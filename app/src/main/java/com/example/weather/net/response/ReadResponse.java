package com.example.weather.net.response;

public class ReadResponse {

    /**
     * code : 1
     * msg : 读取成功
     * data : {"nickname":"test4NickName","sex":"gender","address":"Xxxx","money":"19815"}
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
        return "ReadResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
