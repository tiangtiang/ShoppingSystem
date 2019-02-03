package com.tiang.service;

import com.tiang.dao.BoughtDao;
import com.tiang.dao.CartDao;
import com.tiang.dao.UserDao;
import com.tiang.model.BoughtList;
import com.tiang.model.Cart;
import com.tiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tiang
 * @date 20190130
 * 用户相关服务
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BoughtDao boughtDao;

    @Autowired
    private CartDao cartDao;

    /**
     * 通过用户id查询用户的信息
     * @param id 用户id
     * @return 用户信息
     */
    public User queryUser(int id){
        return userDao.queryUserById(id);
    }

    /**
     * 通过用户名查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    public User queryUser(String userName){
        return userDao.queryUserByName(userName);
    }

    /**
     * 查询用户的购买列表
     * @param user 用户
     */
    public void queryUserBought(User user){
        List<BoughtList> lists = boughtDao.queryBoughtList(user.getUserId());
        user.setBoughtLists(lists);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(){
        userDao.deleteUser(5);
        throwEx();
        userDao.deleteUser(6);
    }

    /**
     * 查询用户购物车中是否存在某个商品
     * @param userId 用户id
     * @param commodityId 商品id
     * @return 如果有就返回这条记录，如果没有就返回null
     */
    public Cart cartExistCommodity(int userId, int commodityId){
        Cart cart = cartDao.queryCart(userId, commodityId);
        return cart;
    }

    /**
     * 更新购物车中商品的数量
     * @param cart 购物车记录
     * @return 是否更新成功
     */
    public boolean updateCartCommodityCount(Cart cart){
        int result = cartDao.updateCommodityCount(cart);
        return result > 0;
    }

    /**
     * 向购物车中添加记录
     * @param cart 购物车记录
     * @return 是否插入成功
     */
    public boolean addCommodityToCart(Cart cart){
        return cartDao.addCommodityToCart(cart)>0;
    }

    /**
     * 查询用户的购物车列表
     * @param userId 用户id
     * @return 购物车列表信息
     */
    public List<Cart> queryUserCart(int userId){
        return cartDao.queryCartListWithCommodity(userId);
    }

    public int count(){
        return userDao.count();
    }

    private void throwEx(){
        throw new RuntimeException("error occured");
    }
}
