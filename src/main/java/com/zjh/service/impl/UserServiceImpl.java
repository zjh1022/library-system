package com.zjh.service.impl;

import com.zjh.mapper.UserMapper;
import com.zjh.pojo.User;
import com.zjh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:48
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public int updateUserByUsername(User user) {
        return userMapper.updateUserByUsername(user);
    }
}
