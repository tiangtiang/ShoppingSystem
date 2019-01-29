package com.tiang.controller;

import com.tiang.model.Commodity;
import com.tiang.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private CommodityService service;

    @RequestMapping("/index")
    public String index(ModelMap map){
        List<Commodity> list = service.queryList();
        map.put("goods", list);
        return "index";
    }

    @RequestMapping("/index/image/{id}")
    public void queryImage(@PathVariable int id, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        response.getOutputStream().write(service.queryImage(id));
    }
}
