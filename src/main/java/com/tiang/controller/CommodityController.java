package com.tiang.controller;

import com.tiang.model.BoughtList;
import com.tiang.model.Commodity;
import com.tiang.model.User;
import com.tiang.service.CommodityService;
import com.tiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
}
