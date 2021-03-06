package com.tiang.controller;

import com.tiang.interceptor.RequiredLogin;
import com.tiang.interceptor.UserType;
import com.tiang.model.BoughtList;
import com.tiang.model.Cart;
import com.tiang.model.Commodity;
import com.tiang.model.User;
import com.tiang.service.CommodityService;
import com.tiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author tiang
 * @date 20190201
 * 展示商品的页面
 */
@Controller
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private UserService userService;

    /**
     * 根据商品id查询商品的详细信息
     * @param id 商品id
     */
    @RequestMapping("/commodity")
    public String showCommodity(@RequestParam int id, ModelMap map, HttpSession session){
        Commodity commodity = commodityService.queryCommodity(id);
        map.put("commodity", commodity);

        if(session.getAttribute("user") != null){
            // 如果用户已经登录
            User user = (User) session.getAttribute("user");
            if(user.getIsBuyer() == 1){
                // 如果用户是买家
                // 查询用户购买的商品
                userService.queryUserBought(user);
                BoughtList bl = user.findBoughtList(id);
                if(bl!=null){
                    // 用户已经购买了该商品
                    map.put("bought", bl);
                }
            }
            map.put("user", user);
        }

        return "commodity-info";
    }

    /**
     * 将商品加入购物车
     * @param commodityId 商品id
     * @param count 购买数量
     */
    @RequestMapping(path = "/commodity/add.do", method = RequestMethod.POST)
    @ResponseBody
    @RequiredLogin
    public String addCommodityToCart(int commodityId, int count, HttpSession session){
        if(session.getAttribute("user")!=null){
            User user = (User)session.getAttribute("user");
            Cart cart = userService.cartExistCommodity(user.getUserId(), commodityId);
            if(cart != null){
                // 购物车中已经有了这件产品，就更新
                cart.setCount(cart.getCount()+count);
                if(userService.updateCartCommodityCount(cart))
                    return "success";
            }else{
                // 不存在，就插入
                cart = new Cart();
                cart.setUserId(user.getUserId());
                cart.setCommodityId(commodityId);
                cart.setCount(count);
                cart.setAddTime(new Date());
                if(userService.addCommodityToCart(cart)){
                    return "success";
                }
            }
        }
        return "failed";
    }

    /**
     * 删除商品
     * @param id 商品id
     * @param session 会话信息
     * @return 删除成功与否
     */
    @RequestMapping("/commodity/del")
    @RequiredLogin(UserType.SELLER)
    @ResponseBody
    public String deleteCommodity(int id, HttpSession session){
        int result = userService.deleteCommodity(id);
        if(result != -1)
            return "success";
        else
            return "failed";
    }
}
