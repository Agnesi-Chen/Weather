package com.example.weather.net.response;


/**
 * 用户信息类，有五个字段
 */
public class UserInformation {

    /**
     * name : zhangsan
     * nickname : 张三
     * sex : male
     * address : 1112a
     * money : 100
     */

    private String name;
    private String nickname;
    private String sex;
    private String address;
    private String money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
