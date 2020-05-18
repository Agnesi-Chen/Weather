package com.example.weather.net;

import com.example.weather.net.response.LoginResponse;
import com.example.weather.net.response.ReadResponse;
import com.example.weather.net.response.RegisterResponse;
import com.example.weather.net.response.UpdateResponse;
import com.example.weather.net.response.UserInformation;

import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;

public class UserController {
    private static final UserController ourInstance = new UserController();

    public static UserController getInstance() {
        return ourInstance;
    }

    private UserController() {
    }


    /**
     * 注册接口
     *
     * @param name     姓名 必填
     * @param password 密码 必填
     * @param nickname 昵称 选填(可以为null)
     * @param sex      性别 选填(可以为null)
     * @param address  地址 选填(可以为null)
     * @param callback 请求回调，必填
     */
    public void register(String name,
                         String password,
                         String nickname,
                         String sex,
                         String address, Callback<RegisterResponse> callback) {
        HashMap<String, String> options = new HashMap<>();
        options.put("name", name);
        options.put("password", password);
        if (nickname != null) {
            options.put("nickname", nickname);
        }
        if (sex != null) {
            options.put("sex", sex);
        }
        if (address != null) {
            options.put("address", address);
        }
        RetrofitApi.getInstance().getRequester().insert(options).enqueue(callback);
    }


    /**
     * 更新信息接口
     *
     * @param name     姓名 必填
     * @param nickname 昵称 选填(可以为null)
     * @param sex      性别 选填(可以为null)
     * @param address  地址 选填(可以为null)
     * @param money    余额 选填(可以为null)
     * @param callback 请求回调，必填
     */
    public void update(String name,
                       String nickname,
                       String sex,
                       String address,
                       String money,
                       Callback<UpdateResponse> callback) {
        HashMap<String, String> options = new HashMap<>();
        options.put("name", name);
        if (nickname != null) {
            options.put("nickname", nickname);
        }
        if (sex != null) {
            options.put("sex", sex);
        }
        if (address != null) {
            options.put("address", address);
        }
        if (money != null) {
            options.put("money", money);
        }
        RetrofitApi.getInstance().getRequester().update(options).enqueue(callback);
    }


    /**
     * 登录接口
     *
     * @param name     姓名 必填
     * @param password 密码 必填
     * @param callback 请求回调，必填
     */
    public void login(String name,
                      String password,
                      Callback<LoginResponse> callback) {
        HashMap<String, String> options = new HashMap<>();
        options.put("name", name);
        options.put("password", password);
        RetrofitApi.getInstance().getRequester().login(options).enqueue(callback);
    }

    /**
     * 查询信息接口
     *
     * @param name     姓名 必填
     * @param callback 请求回调，必填
     */
    public void getInformationByName(String name,
                                     Callback<ReadResponse> callback) {
        RetrofitApi.getInstance().getRequester().read(name).enqueue(callback);
    }

    /**
     * 查询所有成员接口
     *
     * @param callback 请求回调，必填
     */
    public void getAllInformation(Callback<List<UserInformation>> callback) {
        RetrofitApi.getInstance().getRequester().readAll().enqueue(callback);
    }
}
