package com.tiang.model;

import java.util.Date;
import java.util.List;

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

    private Date createTime;
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    // 购物车
    private List<Cart> carts;
    // 已购列表
    private List<BoughtList> boughtLists;

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

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<BoughtList> getBoughtLists() {
        return boughtLists;
    }

    public void setBoughtLists(List<BoughtList> boughtLists) {
        this.boughtLists = boughtLists;
    }

    /**
     * 判断用户是否已购买某个商品
     * @param cid 商品id
     * @return 如果已购买就返回商品信息，如果未购买就返回null
     */
    public BoughtList findBoughtList(int cid){
        for(BoughtList bl: boughtLists){
            if(bl.getCommodityId() == cid)
                return bl;
        }
        return null;
    }
}
