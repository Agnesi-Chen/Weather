package com.example.weather.setting.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.weather.R;
import com.example.weather.UserInformationMaintainer;
import com.example.weather.net.UserController;
import com.example.weather.net.response.UpdateResponse;
import com.example.weather.setting.BalanceActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 充值类
 * 连接数据库服务器接口实现修改充值
 * 2020/5/18
 */

public class RechargeActivity extends Activity {

    private EditText edit_money;
    private Button back,re,one,three,five,ten,twenty,fifty,hundred,th,fh;
    private String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        init();
        Jump();
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
    //初始化各个控件
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
    //界面跳转
    private void Jump(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到余额界面
                Intent intent = new Intent(RechargeActivity.this, BalanceActivity.class);
                startActivity(intent);
            }
        });
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳回界面
                //加上当前已经有的余额，计算出最终余额
                String currentMoney = getIntent().getStringExtra("currentMoney");
                try {
                    int totalMoney = Integer.valueOf(currentMoney) + Integer.valueOf(edit_money.getText().toString());
                    //请求修改信息接口修改用户名
                    if (totalMoney == 0) {
                        Toast.makeText(RechargeActivity.this, "充值金额不能为0", Toast.LENGTH_SHORT).show();
                    } else {
                        UserController.getInstance().update(UserInformationMaintainer.currentUserName, null, null, null, String.valueOf(totalMoney), new Callback<UpdateResponse>() {
                            @Override
                            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                                Toast.makeText(RechargeActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                                Toast.makeText(RechargeActivity.this, "充值失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(RechargeActivity.this, "请输入有效金额", Toast.LENGTH_SHORT).show();
                }
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "1";
                edit_money.setText(money);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "3";
                edit_money.setText(money);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "5";
                edit_money.setText(money);
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "10";
                edit_money.setText(money);
            }
        });
        twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "20";
                edit_money.setText(money);
            }
        });
        fifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "50";
                edit_money.setText(money);
            }
        });
        hundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "100";
                edit_money.setText(money);
            }
        });
        th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "300";
                edit_money.setText(money);
            }
        });
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money = "500";
                edit_money.setText(money);
            }
        });
    }
}
