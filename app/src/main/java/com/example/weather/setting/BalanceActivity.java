package com.example.weather.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.weather.R;
import com.example.weather.UserInformationMaintainer;
import com.example.weather.net.UserController;
import com.example.weather.net.response.ReadResponse;
import com.example.weather.setting.personal.RechargeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 余额类
 * 连接数据库服务器查看个人余额
 * 2020/5/18
 */

public class BalanceActivity extends Activity {

    private Button recharge,back;
    private TextView money;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        init();
        Jump();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }
    //初始化各个控件
    private void init(){
        back = findViewById(R.id.back);
        money = findViewById(R.id.money);
        recharge = findViewById(R.id.recharge);
    }
    //界面跳转
    private void Jump(){
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
    //修改余额
    @Override
    protected void onResume() {
        super.onResume();
        UserController.getInstance().getInformationByName(UserInformationMaintainer.currentUserName, new Callback<ReadResponse>() {
            @Override
            public void onResponse(Call<ReadResponse> call, Response<ReadResponse> response) {
                money.setText(response.body().getData().getMoney());
            }

            @Override
            public void onFailure(Call<ReadResponse> call, Throwable t) {
                Toast.makeText(BalanceActivity.this,"余额刷新失败",Toast.LENGTH_SHORT).show();
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
