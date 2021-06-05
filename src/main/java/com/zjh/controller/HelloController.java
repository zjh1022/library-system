package com.zjh.controller;

import com.zjh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther zheng jianghai
 * @create 2021-05-23 13:29
 */
@Controller
public class HelloController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String hello() {
        return "login";
    }

    @GetMapping("/index")
    public String hello1() {
        return "index";
    }


}
