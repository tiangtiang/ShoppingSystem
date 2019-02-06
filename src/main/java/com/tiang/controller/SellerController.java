package com.tiang.controller;

import com.tiang.interceptor.RequiredLogin;
import com.tiang.interceptor.UserType;
import com.tiang.model.Commodity;
import com.tiang.model.User;
import com.tiang.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author tiang
 * @date 20190129
 * 卖家界面
 */
@Controller
public class SellerController {

    // 关于商品的服务
    @Autowired
    private CommodityService service;

    /**
     * 新增商品
     * @param title 标题
     * @param summary 摘要
     * @param content 内容
     * @param price 价格
     * @return 跳转页面，成功就跳转到首页，失败就跳转到错误页面
     * @throws IOException 读取图片可能出现异常
     */
    @RequestMapping("/add")
    @RequiredLogin(UserType.SELLER)
    public String addCommodity(String title, String summary, String content, double price,
                               MultipartFile file, String imgUrl, HttpSession session) throws IOException {
        Commodity commodity = new Commodity();
        commodity.setTitle(title);
        commodity.setSummary(summary);
        commodity.setContent(content);
        commodity.setPrice(price);
        if(file!=null)
            commodity.setImage(file.getBytes());
        if(imgUrl!=null)
            commodity.setImgUrl(imgUrl);
        User user = (User) session.getAttribute("user");
        commodity.setOwnerId(user.getUserId());

        int result = service.addCommodity(commodity);
        if(result!=-1)
            return "failed";
        else
            return "success";
    }

    @RequestMapping("/public")
    @RequiredLogin(UserType.SELLER)
    public String publicCommodity(HttpSession session){
        return "public-commodity";
    }
}
