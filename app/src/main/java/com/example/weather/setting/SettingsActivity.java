package com.example.weather.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.example.weather.MeActivity;
import com.example.weather.R;

public class SettingsActivity extends Activity {

    private Button back,accounts,information,balance,notice,suggestion,use,about;
    private Button in_accounts,in_information,in_balance,in_notice,in_suggestion,in_use,in_about;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到上一界面
                Intent intent = new Intent(SettingsActivity.this, MeActivity.class);
                startActivity(intent);
            }
        });
        accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到账号设置界面
                Intent intent = new Intent(SettingsActivity.this,AccountsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        in_accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到账号设置界面
                Intent intent = new Intent(SettingsActivity.this,AccountsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到消息设置界面
                Intent intent = new Intent(SettingsActivity.this,InformationActivity.class);
                startActivity(intent);
            }
        });
        in_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到消息设置界面
                Intent intent = new Intent(SettingsActivity.this,InformationActivity.class);
                startActivity(intent);
            }
        });
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到余额界面
                Intent intent = new Intent(SettingsActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });
        in_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到余额界面
                Intent intent = new Intent(SettingsActivity.this,BalanceActivity.class);
                startActivity(intent);
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到通知栏界面
                Intent intent = new Intent(SettingsActivity.this,NoticeActivity.class);
                startActivity(intent);
            }
        });
        in_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到通知栏界面
                Intent intent = new Intent(SettingsActivity.this,NoticeActivity.class);
                startActivity(intent);
            }
        });
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到意见反馈界面
                Intent intent = new Intent(SettingsActivity.this,SuggestionActivity.class);
                startActivity(intent);
            }
        });
        in_suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到意见反馈界面
                Intent intent = new Intent(SettingsActivity.this,SuggestionActivity.class);
                startActivity(intent);
            }
        });
        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到使用教程界面
                Intent intent = new Intent(SettingsActivity.this,UseActivity.class);
                startActivity(intent);
            }
        });
        in_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到使用教程界面
                Intent intent = new Intent(SettingsActivity.this,UseActivity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到关于的界面
                Intent intent = new Intent(SettingsActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        in_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到关于的界面
                Intent intent = new Intent(SettingsActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        back = findViewById(R.id.back);
        accounts = findViewById(R.id.accounts);
        in_accounts = findViewById(R.id.in_accounts);
        information = findViewById(R.id.information);
        in_information = findViewById(R.id.in_information);
        balance = findViewById(R.id.balance);
        in_balance = findViewById(R.id.in_balance);
        notice = findViewById(R.id.notice);
        in_notice = findViewById(R.id.in_notice);
        suggestion = findViewById(R.id.suggestion);
        in_suggestion = findViewById(R.id.in_suggestion);
        use = findViewById(R.id.use);
        in_use = findViewById(R.id.in_use);
        about = findViewById(R.id.about);
        in_about = findViewById(R.id.in_about);
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this,MeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
}
