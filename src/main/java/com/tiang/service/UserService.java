package com.tiang.service;

import com.tiang.dao.UserDao;
import com.tiang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public User queryUser(int id){
        return dao.queryUser(id);
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
