package com.example.weather.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.example.weather.R;

public class UseActivity extends Activity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use);
        back  = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到上一界面
                Intent intent = new Intent(UseActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
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
}
