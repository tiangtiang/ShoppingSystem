package com.tiang.controller;

import com.tiang.interceptor.RequiredLogin;
import com.tiang.model.BoughtList;
import com.tiang.model.User;
import com.tiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tiang
 * @date 20190204
 * 购买列表
 */
@Controller
public class BoughtController {

    @Autowired
    private UserService userService;

    @RequestMapping("/bought")
    @RequiredLogin
    public String showList(ModelMap map, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<BoughtList> list = userService.queryUserBoughtWithCommodity(user.getUserId());
        map.put("bought", list);
        return "show-bought";
    }
}
