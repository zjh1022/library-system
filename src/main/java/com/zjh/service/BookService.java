package com.zjh.service;

import com.zjh.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:44
 */

public interface BookService {
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

    //查询有几本图书
    int selectCount();
}
