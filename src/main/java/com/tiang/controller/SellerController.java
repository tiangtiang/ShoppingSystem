package com.tiang.controller;

import com.tiang.model.Commodity;
import com.tiang.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
     * @param image 图片
     * @return 跳转页面，成功就跳转到首页，失败就跳转到错误页面
     * @throws IOException 读取图片可能出现异常
     */
    @RequestMapping("/add")
    public String addCommodity(String title, String summary, String content, double price,
                             MultipartFile image) throws IOException {
        Commodity commodity = new Commodity();
        commodity.setTitle(title);
        commodity.setSummary(summary);
        commodity.setContent(content);
        commodity.setPrice(price);
        commodity.setImage(image.getBytes());
        commodity.setOwnerId(2);

        int result = service.addCommodity(commodity);
        if(result!=-1)
            return "redirect:../index";
        else
            return "redirect:../html/error.html";
    }

    @RequestMapping("/public")
    public String publicCommodity(){
        return "public-commodity";
    }
}
