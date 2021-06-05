package com.zjh.mapper;

import com.zjh.pojo.Reader;
import com.zjh.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-21 20:13
 */
@Mapper
@Repository
public interface ReaderMapper {
    //根据卡号查询读者
    Reader selectReaderByUsername(String username);

    //查询所有读者
    List<Reader> selectAllReader();

    //插入读者信息
    int insertReader(Reader reader);

    //根据id更改读者状态
    int updateReaderState(@Param("readerId") int booKId, @Param("state") String state);

    //更改读者信息
    Reader updateReader(Reader reader);

    //搜索读者信息
    List<Reader> selectLike(String keywords);
}
