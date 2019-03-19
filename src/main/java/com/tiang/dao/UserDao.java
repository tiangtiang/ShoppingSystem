package com.tiang.dao;

import com.tiang.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author tiang
 * @date 20190130
 * 用户操作相关
 */
@Repository
public interface UserDao {

    /**
     * 通过用户id查询用户的信息
     * @param id 用户id
     * @return 用户信息
     */
    User queryUserById(int id);

    /**
     * 通过用户名来查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    User queryUserByName(String userName);

    /**
     * 删除用户
     * @param id 用户id
     */
    void deleteUser(int id);

    /**
     * 查询用户数量
     * @return 用户数量
     */
    int count();
}
