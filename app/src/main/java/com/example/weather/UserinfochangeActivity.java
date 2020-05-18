package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 管理员更改个人信息类
 * 连接数据库服务器以管理员身份修改个人信息
 * 2020/5/18
 */

public class UserinfochangeActivity extends Activity {

    private Button back,change;
    private EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfochange);
        init();
        Jump();
    }
    //初始化控件
    private void init(){
        back = findViewById(R.id.back);
        change = findViewById(R.id.change);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
    }
    //界面跳转
    private void Jump(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到管理员页面
                Intent intent = new Intent(UserinfochangeActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到管理员页面
                Intent intent = new Intent(UserinfochangeActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode, event);
    }
}
