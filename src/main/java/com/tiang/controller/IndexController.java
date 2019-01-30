package com.tiang.controller;

import com.tiang.model.Commodity;
import com.tiang.model.User;
import com.tiang.service.CommodityService;
import com.tiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author tiang
 * @date 20190129
 * 主页相关的页面
 */
@Controller
public class IndexController {

    // 商品相关服务
    @Autowired
    private CommodityService commodityService;
    // 用户相关服务
    @Autowired
    private UserService userService;

    /**
     * 首页展示商品信息
     * @param map 商品信息列表
     * @return 商品页面地址
     */
    @RequestMapping("/index")
    public String index(ModelMap map){
        List<Commodity> list = commodityService.queryList();
        map.put("goods", list);
        return "index";
    }

    /**
     * 查询商品对应的图片
     * @param id 商品id
     * @param response http响应
     * @throws IOException 可能抛出的异常
     */
    @RequestMapping("/index/image/{id}")
    public void queryImage(@PathVariable int id, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        response.getOutputStream().write(commodityService.queryImage(id));
    }

    /**
     * 处理用户登录
     * @param userName 用户名
     * @param password 密码
     * @return 登录是否成功
     */
    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(String userName, String password){
        User user = userService.queryUser(userName);
        if(user.getPassword() .equals(password))
            return user;
        else
            return "failed";
    }
}
