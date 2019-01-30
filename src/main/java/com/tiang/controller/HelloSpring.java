package com.tiang.controller;

import com.tiang.model.User;
import com.tiang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/spring")
public class HelloSpring {

    @Autowired
    private UserService service;

    @RequestMapping("/hello/{id}" )
    public void hello(@PathVariable int id, PrintWriter writer){
        User user = service.queryUser(id);
        writer.println("username: "+user.getUserName());
    }

    @RequestMapping("/count")
    public void count(HttpServletResponse response) throws IOException {
        try {
            service.delete();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        int count = service.count();
        response.setContentType("text/plain;charset=utf-8");
        response.getWriter().println("当前用户数量："+count);
    }
}
