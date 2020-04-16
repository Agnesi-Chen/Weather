package com.example.weather.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.weather.LoginActivity;
import com.example.weather.R;
import com.example.weather.setting.personal.EditNameActivity;
import com.example.weather.setting.personal.Hometown_LocationActivity;
import com.example.weather.setting.personal.ImageActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccountsActivity extends Activity {

    private Button back,pic,name,sex,location,sercet,turnoff,logoff;
    private Button in_pic,in_name,in_sex,in_location,in_sercet,in_turnoff,in_logoff;
    private TextView tv;
    private String[] sexArry = new String[]{"男","女"};
    private static String sex_choice,url,url2,id,mysex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        init();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        read();
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
        in_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, EditNameActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        in_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, EditNameActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_sex();
            }
        });
        in_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_sex();
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到居住地址
                Intent intent = new Intent(AccountsActivity.this,Hometown_LocationActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        in_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到居住地址
                Intent intent = new Intent(AccountsActivity.this, Hometown_LocationActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        turnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnLogin();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(AccountsActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        back = findViewById(R.id.back);
        pic = findViewById(R.id.picture);
        in_pic = findViewById(R.id.in_picture);
        name = findViewById(R.id.name);
        in_name = findViewById(R.id.in_name);
        sex = findViewById(R.id.sex);
        tv = findViewById(R.id.tv);
        in_sex = findViewById(R.id.in_sex);
        location = findViewById(R.id.location);
        in_location = findViewById(R.id.in_location);
        sercet = findViewById(R.id.sercet);
        in_sercet = findViewById(R.id.in_sercet);
        turnoff = findViewById(R.id.turnoff);
        in_turnoff = findViewById(R.id.in_turnoff);
        logoff = findViewById(R.id.logoff);
        in_logoff = findViewById(R.id.in_logoff);
    }
    //性别选择
    public void change_sex(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:  sex_choice = "男";break;
                    case 1:  sex_choice = "女";break;
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //将数据传回服务器
                requestLogin();
                read();
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public void UnLogin(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定退出吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
    public void requestLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    url = "http://118.31.18.41:8080/TestService/update?name="+id+"&sex"+sex_choice;
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
    public void read() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    url2 = "http://118.31.18.41:8080/TestService/read?name="+id;
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url(url2).build();
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
    private void Json(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject object = new JSONObject(data);
                    JSONObject object1 = object.getJSONObject("data");
                    mysex = object1.getString("sex");
                    tv.setText(mysex);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
