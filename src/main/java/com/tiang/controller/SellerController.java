package com.tiang.controller;

import com.tiang.model.Commodity;
import com.tiang.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private CommodityService service;

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
}
