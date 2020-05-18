package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 管理员类
 * 连接数据库服务器获取所有用户的相关信息
 * 2020/5/18
 */

public class AdminActivity extends Activity {

    private Button back,change;
    private TextView tx11,tx12,tx13,tx14,tx21,tx22,tx23,tx24,tx31,tx32,tx33,tx34;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
        Jump();
    }
    //初始化各个控件
    private void init() {
        change = findViewById(R.id.change);
        back = findViewById(R.id.back);
        tx11 = findViewById(R.id.tx11);
        tx12 = findViewById(R.id.tx12);
        tx13 = findViewById(R.id.tx13);
        tx14 = findViewById(R.id.tx14);
        tx21 = findViewById(R.id.tx21);
        tx22 = findViewById(R.id.tx22);
        tx23 = findViewById(R.id.tx23);
        tx24 = findViewById(R.id.tx24);
        tx31 = findViewById(R.id.tx31);
        tx32 = findViewById(R.id.tx32);
        tx33 = findViewById(R.id.tx33);
        tx34 = findViewById(R.id.tx34);
    }
    //界面跳转
    private void Jump(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到登录界面
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到管理界面
                Intent intent = new Intent(AdminActivity.this, UserinfochangeActivity.class);
                startActivity(intent);
            }
        });
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode, event);
    }
}
