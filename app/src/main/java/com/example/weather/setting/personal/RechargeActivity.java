package com.example.weather.setting.personal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.weather.R;
import com.example.weather.setting.BalanceActivity;

public class RechargeActivity extends AppCompatActivity {

    private EditText edit_money;
    private Button back,re,one,three,five,ten,twenty,fifty,hundred,th,fh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到充值界面
                Intent intent = new Intent(RechargeActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到充值界面
                Intent intent = new Intent(RechargeActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });

    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this, BalanceActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
    private void init(){
        edit_money = findViewById(R.id.edit_money);
        back = findViewById(R.id.back);
        re = findViewById(R.id.re);
        one = findViewById(R.id.one);
        three = findViewById(R.id.three);
        five = findViewById(R.id.five);
        ten = findViewById(R.id.ten);
        twenty = findViewById(R.id.twenty);
        fifty = findViewById(R.id.fifty);
        hundred = findViewById(R.id.hundred);
        th = findViewById(R.id.th);
        fh = findViewById(R.id.fh);
    }
}
