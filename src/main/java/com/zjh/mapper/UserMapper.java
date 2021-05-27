package com.zjh.mapper;

import com.zjh.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @auther zheng jianghai
 * @create 2021-05-21 19:53
 */
@Mapper
@Repository
public interface UserMapper {
    //验证登录
    User selectUserByUsername(String username);

    //修改密码
    int updateUserByUsername(User user);
}
