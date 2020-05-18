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
 * 昵称类
 * 连接数据库服务器修改用户昵称
 * 2020/5/18
 */

public class EditNameActivity extends Activity {

    private Button back,finish;
    private EditText edit_name;
    private String editedName,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        //监听EditText
        edit_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                editedName = edit_name.getText().toString();
            }
        });
        init();
        Jump();
    }
    //初始化各个控件
    private void init(){
        finish = findViewById(R.id.finish);
        back = findViewById(R.id.back);
        edit_name = findViewById(R.id.edit_name);
    }
    //界面跳转
    private void Jump(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(EditNameActivity.this,AccountsActivity.class);
                startActivity(intent);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求修改信息接口修改用户名
                if (TextUtils.isEmpty(editedName)) {
                    Toast.makeText(EditNameActivity.this, "请输入有效的用户名", Toast.LENGTH_SHORT).show();
                } else {
                    UserController.getInstance().update(UserInformationMaintainer.currentUserName, editedName, null, null, null, new Callback<UpdateResponse>() {
                        @Override
                        public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                            Toast.makeText(EditNameActivity.this, "修改昵称成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditNameActivity.this, MeActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }

                        @Override
                        public void onFailure(Call<UpdateResponse> call, Throwable t) {
                            Toast.makeText(EditNameActivity.this, "修改昵称失败", Toast.LENGTH_SHORT).show();
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
