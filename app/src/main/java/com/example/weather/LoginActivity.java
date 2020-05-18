package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.weather.net.UserController;
import com.example.weather.net.response.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 登录类
 * 监听账号、密码的EditText框
 * 监听Checkbox是否选中
 * 连接数据库服务器接口对比账号和密码实现登录
 * 2020/5/18
 */

public class LoginActivity extends Activity {

    private EditText account,sercet;
    private CheckBox checkBox;
    private Button enter;
    public static String name,password,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        Listener();
        //确认登录
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录验证
                if(account.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this, "账号不能为空!", Toast.LENGTH_SHORT).show();
                }
                else if(sercet.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(checkBox.isChecked()) {
                        //连接数据库服务器确认账号和密码
                        Login();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "请先同意隐私协议!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    //初始化各个控件
    private void init(){
        account = findViewById(R.id.account);
        sercet = findViewById(R.id.sercets);
        checkBox = findViewById(R.id.checkbox);
        enter = findViewById(R.id.enter);
    }
    //监听EditText，获取EditText中的值
    private void Listener(){
        account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                name = account.getText().toString();
            }
        });
        sercet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                password = sercet.getText().toString();
            }
        });
    }
    //连接数据库服务器，对账号和密码进行识别
    private void Login(){
        //TODO 缺少管理者功能
        UserController.getInstance().login(name, password, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                if (response.body().getCode() == 1) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //如果是普通用户登录成功，跳转到首页,这里的id其实就是用户名
                    UserInformationMaintainer.currentUserName = name;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码失败", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "网络错误，请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
}
