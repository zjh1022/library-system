package com.zjh.service;

import com.zjh.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:42
 */

public interface UserService {

    //验证登录
    User selectUserByUsername(String username);

    //修改密码
    int updateUserByUsername(User user);
}
