package com.zjh.controller;


import com.zjh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 20:03
 */

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username,String password,String remember,String role,Model model){
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        //封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录的方法
        try {
            if ("on".equals(remember)){
                token.setRememberMe(true);
            }
            session.setAttribute("role",role);
            session.setAttribute("username",username);
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码不正确");
            return "login";
        }

    }
    //注销
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


}
