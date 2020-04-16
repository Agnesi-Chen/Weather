package com.example.weather.setting.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import com.example.weather.R;
import com.example.weather.setting.AccountsActivity;

public class ImageActivity extends Activity {

    private Button back,ok;
    private RadioButton RB1,RB2,RB3,RB4;
    public static final String action = "jason.broadcast.action";
    public static String url,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(ImageActivity.this, AccountsActivity.class);
                startActivity(intent);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(ImageActivity.this, AccountsActivity.class);
                startActivity(intent);
            }
        });

    }
    private void  init(){
        back = findViewById(R.id.back);
        ok = findViewById(R.id.ok);
        RB1 = findViewById(R.id.RB1);
        RB2 = findViewById(R.id.RB2);
        RB3 = findViewById(R.id.RB3);
        RB4 = findViewById(R.id.RB4);
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
