package com.example.weather.setting.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.weather.R;
import com.example.weather.setting.AccountsActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Hometown_LocationActivity extends Activity {

    private Button back,finish;
    private EditText edit_location;
    private static String url,id,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometown__location);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        back = findViewById(R.id.back);
        finish = findViewById(R.id.finish);
        edit_location = findViewById(R.id.edit_location);
        edit_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                location = edit_location.getText().toString();
                requestLogin();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(Hometown_LocationActivity.this, AccountsActivity.class);
                startActivity(intent);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将数据传回服务器
                Intent intent = new Intent(Hometown_LocationActivity.this,AccountsActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }
    //端口连接
    public void requestLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    url = "http://118.31.18.41:8080/TestService/update?name="+id+"&address="+location;//java.net.URLEncoder.encode(name,"utf-8");
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this,AccountsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
}
