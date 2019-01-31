package com.tiang.model;

/**
 * @author tiang
 * @date 20190130
 * 用户类
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private String nickName;

    private int isBuyer;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getIsBuyer() {
        return isBuyer;
    }

    public void setIsBuyer(int buyer) {
        isBuyer = buyer;
    }
}
