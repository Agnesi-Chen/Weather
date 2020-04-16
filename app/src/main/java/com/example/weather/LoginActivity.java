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
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity {

    private EditText account,sercet;
    private CheckBox checkBox;
    private Button enter;
    TextView tv;
    public static String name,password,login_url,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        //监听EditText
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
                requestLogin();
            }
        });
        //监听按钮
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录验证
                if(account.getText().toString().trim().equals("")){
                    toastMessage("账号不能为空!");
                }
                else if(sercet.getText().toString().trim().equals("")){
                    toastMessage("密码不能为空!");
                }
                else{
                    if(checkBox.isChecked()) {
                        if (sercet.getText().toString().trim().equals("123")){//(msg.equals("登陆成功")){
                            if(account.getText().toString().trim().equals("Admin")){
                                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("id",name);
                                startActivity(intent);
                            }

                        } else {
                            toastMessage("账号或密码错误,请重试!");
                        }
                    }
                    else{
                        toastMessage("请先同意隐私协议!");
                    }
                }
            }
        });
    }
    //初始化
    private void init(){
        account = findViewById(R.id.account);
        sercet = findViewById(R.id.sercets);
        checkBox = findViewById(R.id.checkbox);
        enter = findViewById(R.id.enter);
        tv = findViewById(R.id.tv);
    }
    public void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    //端口连接
    public void requestLogin() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                login_url = "http://118.31.18.41:8080/TestService/login_date?name="+name+"&password="+password;
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(login_url).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    Json(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //Json数据解析
    private void Json(final String responsedata){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject jsonObject = new JSONObject(responsedata);
                    msg = jsonObject.getString("msg");
                }catch (JSONException e){
                    e.printStackTrace();
                }
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
