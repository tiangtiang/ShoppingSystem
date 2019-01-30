package com.tiang.service;

import com.tiang.dao.UserDao;
import com.tiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tiang
 * @date 20190130
 * 用户相关服务
 */
@Service
public class UserService {

    @Autowired
    private UserDao dao;

    /**
     * 通过用户id查询用户的信息
     * @param id 用户id
     * @return 用户信息
     */
    public User queryUser(int id){
        return dao.queryUserById(id);
    }

    /**
     * 通过用户名查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    public User queryUser(String userName){
        return dao.queryUserByName(userName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(){
        dao.deleteUser(5);
        throwEx();
        dao.deleteUser(6);
    }

    public int count(){
        return dao.count();
    }

    private void throwEx(){
        throw new RuntimeException("error occured");
    }
}
