package com.tiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;

@Controller
@RequestMapping("/spring")
public class HelloSpring {

    @RequestMapping("/hello")
    public void hello(PrintWriter writer){
        writer.println("Hello, world!");
    }
}
