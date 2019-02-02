package com.tiang.service;

import com.tiang.dao.BoughtDao;
import com.tiang.dao.UserDao;
import com.tiang.model.BoughtList;
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

    public int count(){
        return userDao.count();
    }

    private void throwEx(){
        throw new RuntimeException("error occured");
    }
}
