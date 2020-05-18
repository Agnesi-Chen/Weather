package com.example.weather.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类，单例模式，创建了请求器对象
 * 在构造里面就创建了请求器
 * 通过getRequester函数将请求器暴露出去
 *   .addConverterFactory(GsonConverterFactory.create())
 * 这个代码是配置将后台返回的json自动转化成javabean类的，比如后台返回的json长这个样子：
 *{"code":1,
 * "msg":"修改成功",
 * "data":{"nickname":"test1NickName","sex":"1","address":"xxxxxxxxxxxxx"}}
 * 这个转换器就会自动解析成java bean,解析出来对应的就是UpdateResponse.java 这个bean类
 */
public class RetrofitApi {
    private static final RetrofitApi ourInstance = new RetrofitApi();


    private final Retrofit retrofit;
    private final TestService testService;

    public static RetrofitApi getInstance() {
        return ourInstance;
    }

    private RetrofitApi() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .retryOnConnectionFailure(true);
        retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl("http://101.200.127.105:8080/TestService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        testService = retrofit.create(TestService.class);
    }



    public TestService getRequester(){
        return testService;
    }




}
