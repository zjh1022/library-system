package com.zjh.service.impl;

import com.zjh.mapper.BookMapper;
import com.zjh.pojo.Book;
import com.zjh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:47
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Book> selectAllBook() {
        return bookMapper.selectAllBook();
    }

    @Override
    public int insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Book selectBookById(int bookId) {
        return bookMapper.selectBookById(bookId);
    }

    @Override
    public int deleteBookById(int bookId) {
        return bookMapper.deleteBookById(bookId);
    }

    @Override
    public List<Book> selectLike(String keyword) {
        return bookMapper.selectLike(keyword);
    }

    @Override
    public int selectCount() {
        return bookMapper.selectCount();
    }
}
