package com.tiang.controller;

import com.tiang.model.Commodity;
import com.tiang.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tiang
 * @date 20190201
 * 展示商品的页面
 */
@Controller
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    /**
     * 根据商品id查询商品的详细信息
     * @param id 商品id
     */
    @RequestMapping("/commodity")
    public String showCommodity(@RequestParam int id, ModelMap map){
        Commodity commodity = commodityService.queryCommodity(id);
        map.put("commodity", commodity);
        return "commodity-info";
    }
}
