package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocationActivity extends Activity {

    private EditText edit_locaton;
    private Button back,BJ,SH,CD,GZ,SZ,HZ,CQ,WH,XA,NJ,CS,SY,finish;
    private static String location = "上海";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到主界面
                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到主界面
                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                intent.putExtra("location",location);
                startActivity(intent);

            }
        });
        BJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "北京";
                edit_locaton.setText(location);
            }
        });
        SH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "上海";
                edit_locaton.setText(location);
            }
        });
        CD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "成都";
                edit_locaton.setText(location);
            }
        });
        GZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "广州";
                edit_locaton.setText(location);
            }
        });
        SZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "深圳";
                edit_locaton.setText(location);
            }
        });
        HZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "杭州";
                edit_locaton.setText(location);
            }
        });
        CQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "重庆";
                edit_locaton.setText(location);
            }
        });
        WH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "武汉";
                edit_locaton.setText(location);
            }
        });
        XA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "西安";
                edit_locaton.setText(location);
            }
        });
        NJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "南京";
                edit_locaton.setText(location);
            }
        });
        CS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "长沙";
                edit_locaton.setText(location);
            }
        });
        SY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "沈阳";
                edit_locaton.setText(location);
            }
        });
    }
    private void init(){
        edit_locaton = findViewById(R.id.edit_location);
        back = findViewById(R.id.back);
        finish = findViewById(R.id.finish);
        BJ = findViewById(R.id.BJ);
        SH = findViewById(R.id.SH);
        CD = findViewById(R.id.CD);
        GZ = findViewById(R.id.GZ);
        SZ = findViewById(R.id.SZ);
        HZ = findViewById(R.id.HZ);
        CQ = findViewById(R.id.CQ);
        WH = findViewById(R.id.WH);
        XA = findViewById(R.id.XA);
        NJ = findViewById(R.id.NJ);
        CS = findViewById(R.id.CS);
        SY = findViewById(R.id.SY);
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
}
