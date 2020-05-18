package com.example.weather.net.response;

/**
 *{"code":1,
 * "msg":"修改成功",
 * "data":{"nickname":"test1NickName","sex":"1","address":"xxxxxxxxxxxxx"}}
 */
public class UpdateResponse {

    /**
     * code : 1
     * msg : 修改成功
     * data : {"nickname":"test4NickName"}
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
        return "UpdateResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
