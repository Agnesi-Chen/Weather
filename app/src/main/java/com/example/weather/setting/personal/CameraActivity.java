package com.example.weather.setting.personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.core.content.FileProvider;
import com.example.weather.MainActivity;
import com.example.weather.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class CameraActivity extends Activity {

    private ImageView iv;
    private Button back, ok, again;
    private String result;
    private Uri imageUri;
    private Bitmap bitmap;

    static {
        System.loadLibrary("tensorflow_inference");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        init();
        openCamera();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到主界面
                Intent intent = new Intent(CameraActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //确定上传
                Classifier classifier = TensorFlowImageClassifier.create(CameraActivity.this);
                final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap);
                result = results.toString();
                Intent intent = new Intent(CameraActivity.this, ResultActivity.class);
                intent.putExtra("result",result);
                startActivity(intent);
            }
        });
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCamera();
            }
        });
    }
    private void openCamera() {
        //创建file对象
        File outputImage = new File(getExternalCacheDir(),"output_image");
        try{
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT >= 24){
            imageUri = FileProvider.getUriForFile(this,"com.example.weather.fileprovider",outputImage);
        }
        else {
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);// 启动系统相机
        startActivityForResult(intent,1);
    }
    // 获取结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                try{
                    //将拍摄的照片显示出来
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    iv.setImageBitmap(bitmap);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    private void init() {
        iv = findViewById(R.id.iv);
        ok = findViewById(R.id.ok);
        again = findViewById(R.id.again);
        back = findViewById(R.id.back);
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
