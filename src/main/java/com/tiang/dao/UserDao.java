package com.tiang.dao;

import com.tiang.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    @Select("select * from t_user where id=#{id}")
    User queryUser(int id);

    @Delete("delete from t_user where id=#{id}")
    void deleteUser(int id);

    @Select("select count(*) from t_user")
    int count();
}
