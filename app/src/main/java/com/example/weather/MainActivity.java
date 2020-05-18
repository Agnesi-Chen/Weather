package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import interfaces.heweather.com.interfacesmodule.bean.Lang;
import interfaces.heweather.com.interfacesmodule.bean.Unit;
import interfaces.heweather.com.interfacesmodule.bean.weather.Weather;
import interfaces.heweather.com.interfacesmodule.bean.weather.lifestyle.LifestyleBase;
import interfaces.heweather.com.interfacesmodule.view.HeWeather;
import interfaces.heweather.com.interfacesmodule.view.HeConfig;
import com.example.weather.setting.NoticeActivity;
import com.example.weather.setting.personal.CameraActivity;

/**
 * 主界面类
 * 连接天气接口获取天气预报显示到该界面
 * 2020/5/18
 */

public class MainActivity extends Activity {

    private ImageButton weather, camera, me, news;
    private Button add;
    private TextView location, tmp, hum, dir, pcpn, suggest;
    private String id, loc = "上海";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getWeatherInfo();
        Jump();
    }
    //初始化各个控件
    private void init() {
        weather = findViewById(R.id.weather);
        camera = findViewById(R.id.camera);
        me = findViewById(R.id.me);
        news = findViewById(R.id.news);
        add = findViewById(R.id.add);
        location = findViewById(R.id.location);
        tmp = findViewById(R.id.tmp);
        hum = findViewById(R.id.hum);
        dir = findViewById(R.id.dir);
        suggest = findViewById(R.id.suggest);
        pcpn = findViewById(R.id.pcpn);
    }
    //连接天气接口获取天气预报
    private void getWeatherInfo() {
        /**
         * 这个是android的应用入口类，在这里需要注册key
         * 在和风天气开发者点控制台
         * 可以看到自己创建的应用，
         * 如果以后要改包名的话（build.gradle文件中applicationId "com.example.whether"是包名）
         * 需要在控制台-》应用管理申请Key
         * 这个类需要在androidManifest.xml中注册到application上的name属性
         */
        //这里注册授权sdk
        HeConfig.init("HE2005122237141330", "84927958abee4e3f88189f3e00d40eb1");
        //这里切换到免费开发者模式，缺了这行代码拿不到数据
        HeConfig.switchToFreeServerNode();
        HeWeather.getWeather(MainActivity.this, loc, Lang.CHINESE_SIMPLIFIED, Unit.METRIC, new HeWeather.OnResultWeatherDataListBeansListener() {
            @Override
            public void onError(Throwable throwable) {
                Log.i("deepcodetest", throwable.toString());
            }

            @Override
            public void onSuccess(Weather weather) {
                //在回调成功这里可以接受到请求回来的weather信息
                location.setText(weather.getBasic().getLocation());
                tmp.setText(weather.getNow().getTmp());
                dir.setText(weather.getNow().getWind_dir());
                hum.setText(weather.getNow().getHum());
                pcpn.setText(weather.getNow().getPcpn());
                for (LifestyleBase lifestyleBase : weather.getLifestyle()) {
                    suggest.setText("     " + lifestyleBase.getTxt());
                }
            }
        });
    }
    //界面跳转
    private void Jump(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到定位界面
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到天气页面
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到摄像机
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到我的界面
                Intent intent = new Intent(MainActivity.this, MeActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转消息
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
            }
        });
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode, event);
    }
}
