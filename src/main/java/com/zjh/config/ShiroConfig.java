package com.zjh.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:58
 */

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean:3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager getDefaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(getDefaultWebSecurityManager);

        //添加shiro的内置过滤器
       /*
        anon:无需认证就可以访问
        authc:必须认证了才能访问
        user:必颈拥有记住我功能才能用
        perms :拥有对某个资源的权限才能访问
        role:拥有某个角色权限才能访问
        */
        Map<String,String> filterMap = new LinkedHashMap<>();
        //拦截
        filterMap.put("/index","user");
        filterMap.put("/admin/**","authc");
        filterMap.put("/user/**","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
         bean.setLoginUrl("/toLogin");


        //设置未授权页面
        bean.setUnauthorizedUrl("/404");
       return bean;
    }


    //DefaultWebSecurityManager:2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userReaml
        securityManager.setRealm(userRealm());
        return securityManager;
    }


    //创建UserReaml对象，需要自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合ShiroDialect : 用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    //添加记住我功能
    @Bean
    public SimpleCookie getSimpleCookie(){
        SimpleCookie cookie = new SimpleCookie();
        cookie.setHttpOnly(true);
        cookie.setMaxAge(2592000);
//        System.out.println("getSimpleCookie==>" + cookie);
        return cookie;
    }
}
