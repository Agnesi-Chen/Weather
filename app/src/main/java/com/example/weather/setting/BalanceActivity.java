package com.example.weather.setting;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.weather.R;
import com.example.weather.setting.personal.RechargeActivity;

public class BalanceActivity extends AppCompatActivity {

    private Button recharge,back;
    private TextView money;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        back = findViewById(R.id.back);
        money = findViewById(R.id.money);
        recharge = findViewById(R.id.recharge);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到上一界面
                Intent intent = new Intent(BalanceActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ////跳转到上一界面
                Intent intent = new Intent(BalanceActivity.this, RechargeActivity.class);
                startActivity(intent);
            }
        });
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
}
