package com.example.weather.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.weather.net.response.UpdateResponse;
import com.example.weather.setting.personal.EditNameActivity;
import com.example.weather.setting.personal.Hometown_LocationActivity;
import com.example.weather.setting.personal.ImageActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 个人信息类
 * 展示个人相关信息
 * 修改性别信息
 * 2020/5/18
 */

public class AccountsActivity extends Activity {

    private Button back,pic,name,sex,location,sercet,turnoff,logoff;
    private Button in_pic,in_name,in_sex,in_location,in_sercet,in_turnoff,in_logoff;
    //用于设置性别显示
    private TextView sex_text_view;
    private String[] sexArry = new String[]{"男","女"};
    private static String sex_choice,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        init();
        Jump();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }
    //初始化各个控件
    private void init(){
        back = findViewById(R.id.back);
        pic = findViewById(R.id.picture);
        in_pic = findViewById(R.id.in_picture);
        name = findViewById(R.id.name);
        in_name = findViewById(R.id.in_name);
        sex = findViewById(R.id.sex);
        sex_text_view = findViewById(R.id.tv);
        in_sex = findViewById(R.id.in_sex);
        location = findViewById(R.id.location);
        in_location = findViewById(R.id.in_location);
        sercet = findViewById(R.id.sercet);
        in_sercet = findViewById(R.id.in_sercet);
        turnoff = findViewById(R.id.turnoff);
        in_turnoff = findViewById(R.id.in_turnoff);
        logoff = findViewById(R.id.logoff);
        in_logoff = findViewById(R.id.in_logoff);
    }
    //界面跳转
    private void Jump(){
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
        in_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, EditNameActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        in_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountsActivity.this, EditNameActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_sex();
            }
        });
        in_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_sex();
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到居住地址
                Intent intent = new Intent(AccountsActivity.this,Hometown_LocationActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        in_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到居住地址
                Intent intent = new Intent(AccountsActivity.this, Hometown_LocationActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        turnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnLogin();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到上一界面
                Intent intent = new Intent(AccountsActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * 显示性别选择框
     */
    public void change_sex(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:  sex_choice = "male";break;
                    case 1:  sex_choice = "female";break;
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserController.getInstance().update(UserInformationMaintainer.currentUserName, null, sex_choice, null, null, new Callback<UpdateResponse>() {
                    @Override
                    public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                        Toast.makeText(AccountsActivity.this, "修改性别成功", Toast.LENGTH_SHORT).show();
                        //给性别textView赋值
                        sex_text_view.setText(response.body().getData().getSex());
                    }

                    @Override
                    public void onFailure(Call<UpdateResponse> call, Throwable t) {
                        Toast.makeText(AccountsActivity.this, "修改性别失败", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public void UnLogin(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定退出吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }

}
