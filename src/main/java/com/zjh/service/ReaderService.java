package com.zjh.service;

import com.zjh.pojo.Reader;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:45
 */

public interface ReaderService {
    //根据卡号查询读者
    Reader selectReaderByUsername(String username);

    //查询所有读者
    List<Reader> selectAllReader();

    //插入读者信息
    Reader insertReader(Reader reader);

    //根据id更改读者状态
    int updateReaderState(@Param("readerId") int booKId, @Param("state") String state);

    //更改读者信息
    Reader updateReader(Reader reader);

    //搜索读者信息
    List<Reader> selectLike(String keywords);
}
