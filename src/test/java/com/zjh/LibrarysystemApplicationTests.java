package com.zjh;

import com.zjh.mapper.BookMapper;
import com.zjh.mapper.UserMapper;
import com.zjh.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibrarysystemApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    BookMapper bookMapper;

    @Test
    void contextLoads() {

        int i = bookMapper.selectCount();
        System.out.println(i);
    }


}
