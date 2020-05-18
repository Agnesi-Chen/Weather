package com.example.weather.setting.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.weather.MeActivity;
import com.example.weather.R;
import com.example.weather.UserInformationMaintainer;
import com.example.weather.net.UserController;
import com.example.weather.net.response.UpdateResponse;
import com.example.weather.setting.AccountsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 地址类
 * 连接数据库服务器修改用户地址
 * 2020/5/18
 */

public class Hometown_LocationActivity extends Activity {

    private Button back,finish;
    private EditText edit_location;
    private static String url,id,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometown__location);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        //监听EditText
        edit_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                location = edit_location.getText().toString();
            }
        });
        init();
        Jump();
    }
    //初始化各个控件
    private void init(){
        back = findViewById(R.id.back);
        finish = findViewById(R.id.finish);
        edit_location = findViewById(R.id.edit_location);
    }
    //界面跳转
    private void Jump(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(Hometown_LocationActivity.this, AccountsActivity.class);
                startActivity(intent);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将数据传回服务器
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(Hometown_LocationActivity.this, "请输入有效的地址", Toast.LENGTH_SHORT).show();
                } else {
                    UserController.getInstance().update(UserInformationMaintainer.currentUserName, null, null, location, null, new Callback<UpdateResponse>() {
                        @Override
                        public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                            Toast.makeText(Hometown_LocationActivity.this, "修改地址成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Hometown_LocationActivity.this, MeActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }

                        @Override
                        public void onFailure(Call<UpdateResponse> call, Throwable t) {
                            Toast.makeText(Hometown_LocationActivity.this, "修改地址失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
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
