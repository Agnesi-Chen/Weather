package com.example.weather.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.weather.R;
import com.example.weather.setting.personal.VersionsActivity;

public class AboutActivity extends Activity {

    private Button back,number,edition,friend;
    private Button in_number,in_edition,in_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(AboutActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
        edition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage("当前已是最新版本！");
            }
        });
        in_edition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage("当前已是最新版本！");
            }
        });
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(AboutActivity.this, VersionsActivity.class);
                startActivity(intent);
            }
        });
        in_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(AboutActivity.this, VersionsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        number = findViewById(R.id.number);
        in_number = findViewById(R.id.in_number);
        edition = findViewById(R.id.edition);
        in_edition = findViewById(R.id.in_edition);
        friend = findViewById(R.id.friend);
        in_friend = findViewById(R.id.in_friend);
        back = findViewById(R.id.back);
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
    public void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
