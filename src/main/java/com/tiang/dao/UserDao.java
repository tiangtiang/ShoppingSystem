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
    @Select("select * from t_user where user_id=#{id}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "nickname", property = "nickName")
    })
    User queryUserById(int id);

    /**
     * 通过用户名来查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    @Select("select * from t_user where username=#{userName}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "nickname", property = "nickName")
    })
    User queryUserByName(String userName);

    @Delete("delete from t_user where user_id=#{id}")
    void deleteUser(int id);

    @Select("select count(*) from t_user")
    int count();
}
