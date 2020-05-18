package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.weather.setting.SettingsActivity;
import com.example.weather.setting.personal.CameraActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import com.example.weather.net.UserController;
import com.example.weather.net.response.ReadResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 个人信息展示类
 * 连接数据库服务器获取对应用户的信息展示到相关界面
 * 2020/5/18
 */

public class MeActivity extends Activity {

    private Button back;
    private ImageButton setting;
    private ImageButton weather, camera;
    private TextView name;
    private static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        init();
        Jump();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }
    @Override
    protected void onResume() {
        super.onResume();
        refreshCurrentUserInformation();
    }
    /**
     * 刷新当前用户信息
     */
    protected void refreshCurrentUserInformation(){
        UserController.getInstance().getInformationByName(UserInformationMaintainer.currentUserName, new Callback<ReadResponse>() {
            @Override
            public void onResponse(Call<ReadResponse> call, Response<ReadResponse> response) {
                name.setText(response.body().getData().getNickname());
            }

            @Override
            public void onFailure(Call<ReadResponse> call, Throwable t) {
                Toast.makeText(MeActivity.this,"用户信息刷新失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化各个控件
    private void init() {
        weather = findViewById(R.id.weather);
        camera = findViewById(R.id.camera);
        setting = findViewById(R.id.setting);
        name = findViewById(R.id.name);
        back = findViewById(R.id.back);
    }
    //界面跳转
    private void Jump(){
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到天气页面
                Intent intent = new Intent(MeActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到摄像机
                Intent intent = new Intent(MeActivity.this, CameraActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到主界面
                Intent intent = new Intent(MeActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到设置界面
                Intent intent = new Intent(MeActivity.this, SettingsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
            }
        });
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode, event);
    }
}
