package com.zjh.mapper;

import com.zjh.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-21 20:29
 */
@Mapper
@Repository
public interface BookMapper {

    //查询所有图书
    List<Book> selectAllBook();

    //插入图书
    int insertBook(Book book);

    //修改图书
    int updateBook(Book book);

    //根据id查找图书
    Book selectBookById(@Param("bookId") int bookId);

    //根据id删除图书
    int deleteBookById(@Param("bookId") int bookId);

    //搜索图书
    List<Book> selectLike(String keyword);
}
