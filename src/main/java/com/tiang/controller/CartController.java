package com.tiang.controller;

import com.tiang.interceptor.RequiredLogin;
import com.tiang.model.Cart;
import com.tiang.model.User;
import com.tiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 展示购物车列表
     * @param map 数据
     * @param session 用户信息
     * @return 跳转到购物车页面
     */
    @RequestMapping("/cart")
    @RequiredLogin
    public String showCart(ModelMap map, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Cart> list = userService.queryUserCart(user.getUserId());
        map.put("cart", list);
        return "show-cart";
    }

    @RequestMapping("/cart/buy")
    @RequiredLogin
    @ResponseBody
    public String clearCart(@RequestParam("cids[]") List<Integer> cids,
                            @RequestParam("counts[]") List<Integer> counts,
                            @RequestParam("prices[]") List<Double> prices, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(userService.buyCommodity(user.getUserId(), cids, counts, prices))
            return "success";
        else
            return "failed";
    }
}
