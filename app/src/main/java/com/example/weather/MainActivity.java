package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.weather.setting.NoticeActivity;
import com.example.weather.setting.personal.CameraActivity;

public class MainActivity extends Activity {

    private ImageButton weather, camera, me,news;
    private Button add;
    private TextView location;
    private String id,loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(savedInstanceState != null){
            String s = savedInstanceState.getString("location");
            location.setText(s);
        }
        //按钮
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到定位界面
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到天气页面
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到摄像机
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到我的界面
                Intent intent = new Intent(MainActivity.this, MeActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转消息
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        loc = intent.getStringExtra("location");
        location.setText(loc);

    }
    //初始化
    private void init(){
        weather = findViewById(R.id.weather);
        camera = findViewById(R.id.camera);
        me = findViewById(R.id.me);
        news = findViewById(R.id.news);
        add = findViewById(R.id.add);
        location = findViewById(R.id.location);
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("location",loc);
    }
}
