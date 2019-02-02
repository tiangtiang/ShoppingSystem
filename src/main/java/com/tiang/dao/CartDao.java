package com.tiang.dao;

import com.tiang.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao {


    List<Cart> queryCartList(int userId);
}
