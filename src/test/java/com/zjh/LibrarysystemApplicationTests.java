package com.zjh;

import com.zjh.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibrarysystemApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {

    }


}
