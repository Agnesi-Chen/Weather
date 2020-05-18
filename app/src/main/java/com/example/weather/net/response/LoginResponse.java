package com.example.weather.net.response;

public class LoginResponse {

    /**
     * code : 1
     * msg : 登陆成功
     * data : {"name":"zhangsan","password":"zhangsan"}
     */

    private int code;
    private String msg;
    private UserInformation data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserInformation getData() {
        return data;
    }

    public void setData(UserInformation data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }




}
