package com.example.weather.net;

import com.example.weather.net.response.LoginResponse;
import com.example.weather.net.response.ReadResponse;
import com.example.weather.net.response.RegisterResponse;
import com.example.weather.net.response.UpdateResponse;
import com.example.weather.net.response.UserInformation;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * 这个类声明了网络请求的接口规范
 * 在请求器中已经往框架里注入了baseurl
 * 在这个接口里声明的都是baseurl后面拼接的具体路径以及请求参数
 * 比如read_all这个接口的全路径就是http://101.200.127.105:8080/TestService/(retrofit中注入的baseurl)
 * 加上注解上声明的路径read_all
 * 最终计算出 : http://101.200.127.105:8080/TestService/read_all
 * 然后没有参数
 *
 * https://square.github.io/retrofit/
 * 上面的官方文档里面规定了各种注解的用法
 */
public interface TestService {


    @GET("read_all")
    Call<List<UserInformation>> readAll();

    @GET("read")
    Call<ReadResponse> read(@Query("name") String name);

    @GET("update")
    Call<UpdateResponse> update(@QueryMap Map<String, String> options);

    @GET("insert")
    Call<RegisterResponse> insert(@QueryMap Map<String, String> options);

    @GET("login")
    Call<LoginResponse> login(@QueryMap Map<String, String> options);

}
