package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.weather.setting.SettingsActivity;
import com.example.weather.setting.personal.CameraActivity;
import org.json.JSONException;
import org.json.JSONObject;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MeActivity extends Activity {

    private Button back;
    private ImageButton setting;
    private ImageButton weather, camera;
    private TextView name,tv;
    private CircleImageView image;
    private static String url,id,myname = "",nextname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        init();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nextname = intent.getStringExtra("edit_name");
        /*requestLogin();
        if(nextname == null){
            nextname = myname;
            name.setText(nextname);
        }
        else{
            myname = nextname;
            name.setText(nextname);
        }*/

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到天气页面
                Intent intent = new Intent(MeActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到摄像机
                Intent intent = new Intent(MeActivity.this, CameraActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到主界面
                Intent intent = new Intent(MeActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到设置界面
                Intent intent = new Intent(MeActivity.this, SettingsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("name",nextname);
    }
    @Override
    protected void  onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String s = savedInstanceState.getString("name");
        name.setText(s);
    }
    //服务器端口
    public void requestLogin() {
         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     url = "http://118.31.18.41:8080/TestService/read?name="+id;
                     OkHttpClient okHttpClient = new OkHttpClient();
                     Request request = new Request.Builder().url(url).build();
                     Response response = okHttpClient.newCall(request).execute();
                     String responseData = response.body().string();
                     Json(responseData);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
            }
        }).start();
    }
    //Json数据解析
    private void Json(final String responsedata){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject object = new JSONObject(responsedata);
                    JSONObject object1 = object.getJSONObject("data");
                    System.out.println(object);
                    myname = object1.getString("nickname");
                    name.setText(myname);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
    //初始化
    private void init() {
        weather = findViewById(R.id.weather);
        camera = findViewById(R.id.camera);
        setting = findViewById(R.id.setting);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        back = findViewById(R.id.back);
        tv = findViewById(R.id.tv);
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode, event);
    }
}
