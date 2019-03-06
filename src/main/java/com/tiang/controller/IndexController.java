package com.tiang.controller;

import com.tiang.model.Commodity;
import com.tiang.model.User;
import com.tiang.service.CommodityService;
import com.tiang.service.UserService;
import com.tiang.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    public String index(ModelMap map, HttpSession session, HttpServletRequest request){
        String type = request.getParameter("type");
        if(type!=null && session.getAttribute("user")!=null){
                User user = (User) session.getAttribute("user");
                List<Commodity> list = commodityService.queryCommodityListNotBuy(user.getUserId());
                map.put("user", user);
                map.put("goods", list);
        }else {
            List<Commodity> list = commodityService.queryList();
            map.put("goods", list);
            if (session.getAttribute("user") != null) {
                // 用户已经登录
                User user = (User) session.getAttribute("user");
                if (user.getIsBuyer() == 1) {
                    // 如果是买家
                    // 获取已购列表
                    userService.queryUserBought(user);
                    List<Integer> boughtList =
                            user.getBoughtLists().stream().map(bt -> bt.getCommodityId()).collect(Collectors.toList());
                    map.put("boughtList", boughtList);
                }
                map.put("user", user);
            }
        }
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
     * @param request HTTP请求
     * @return 登录是否成功
     */
    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(String userName, String password, HttpServletRequest request){
        User user = userService.queryUser(userName);
        if(user!=null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            return user;
        }else
            return "failed";
    }

    /**
     * 退出登录
     * @param session HTTP会话
     * @return 重定向到登录界面
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:index";
    }

    @RequestMapping("/register")
    public String register(User user){
        if(userService.addUser(user)){
            return Constant.SUCCESS;
        }else{
            return Constant.FAIL;
        }
    }
}
