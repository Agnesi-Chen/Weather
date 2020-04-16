package com.example.weather.setting.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.weather.MainActivity;
import com.example.weather.R;

public class ResultActivity extends Activity {

    private Button back;
    private TextView result,tv;
    private static String s = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        back = findViewById(R.id.back);
        result = findViewById(R.id.result);
        tv = findViewById(R.id.tv);
        Intent intent = getIntent();
        s = intent.getStringExtra("result");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到主界面
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        if(s.equals("[angry]")){
            result.setText("优");
            tv.setText("今日空气质量很好，适合出门活动。");
        }
        else if(s.equals("[disgusted]")){
            result.setText("良");
            tv.setText("今日空气较好，适合出门活动。");
        }
        else if(s.equals("[fearful]")){
            result.setText("轻度污染");
            tv.setText("今日空气一般，可适当出门活动。");
        }
        else if(s.equals("[happy]")){
            result.setText("中度污染");
            tv.setText("今日空气较差，请减少外出，出门请做好防护准备。");
        }
        else if(s.equals("[sadness]")){
            result.setText("重度污染");
            tv.setText("今日空气很差，不适宜出门。");
        }
    }
    //返回键方法的重写
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keycode,event);
    }
}
