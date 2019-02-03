package com.tiang.controller;

import com.tiang.model.Cart;
import com.tiang.model.User;
import com.tiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tiang
 * @date 20190203
 * 购物车相关
 */
@Controller
public class CartController {

    @Autowired
    private UserService userService;

    @RequestMapping("/cart")
    public Object showCart(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Cart> list = userService.queryUserCart(user.getUserId());
        return list;
    }
}
