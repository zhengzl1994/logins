package com.kido.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {

    @RequestMapping("/index")
    public  String index(){
        System.out.println("我进来啦");
        return "index";
    }
}
