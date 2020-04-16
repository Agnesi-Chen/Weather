package com.example.weather.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.example.weather.R;

public class InformationActivity extends Activity {

    private Button back,DND,news,method,inform;
    private Button in_DND,in_news,in_method,in_inform;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到上一界面
                Intent intent = new Intent(InformationActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        DND = findViewById(R.id.DND);
        in_DND = findViewById(R.id.in_DND);
        news = findViewById(R.id.news);
        in_news = findViewById(R.id.in_news);
        method = findViewById(R.id.method);
        in_method = findViewById(R.id.in_method);
        inform = findViewById(R.id.inform);
        in_inform = findViewById(R.id.in_inform);
        back  = findViewById(R.id.back);
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
