package com.zjh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjh.pojo.AllRecord;
import com.zjh.pojo.Book;
import com.zjh.pojo.Reader;
import com.zjh.pojo.Record;
import com.zjh.service.AllRecordService;
import com.zjh.service.BookService;
import com.zjh.service.ReaderService;
import com.zjh.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.io.Writer;
import java.security.PublicKey;
import java.util.Date;
import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-23 17:13
 */

@Controller
public class ReaderController {

    @Autowired
    BookService bookService;

    @Autowired
    ReaderService readerService;

    @Autowired
    RecordService recordService;

    @Autowired
    AllRecordService allRecordService;



    //图书信息
    @RequestMapping("/reader/book/books")
    public String books(Model model,@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum,5,true);
        List<Book> books = bookService.selectAllBook();
        PageInfo<Book> pageInfo = new PageInfo<Book>(books);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("books", books);
        return "user/books";

    }

    //图书详情
    @RequestMapping("/reader/book/info/{bookId}")
    public String info(@PathVariable("bookId") int bookId, Model model) {
        Book book = bookService.selectBookById(bookId);
        model.addAttribute("book", book);
        return "user/book_info";
    }

    //到达借阅页面
    @RequestMapping("/reader/book/toLend/{bookId}")
    public String toLend(@PathVariable("bookId") int bookId, Model model) {
        Book book = bookService.selectBookById(bookId);
        model.addAttribute("book", book);
        return "user/book_lend";
    }

    //借书
    @RequestMapping("/reader/book/lend/{bookId}/{username}/{state}")
    public String lend(@PathVariable("bookId") int bookId, @PathVariable("username") String username, @PathVariable("state") int state, int days, Model model) {
        Book book = bookService.selectBookById(bookId);
        Reader reader = readerService.selectReaderByUsername(username);
        List<Record> records = recordService.selectRecordByUsername(username);
        List<Record> records1 = allRecordService.selectRecordByUsername(username);
        if (reader.getState()==0){
            if (state == 0) {
                model.addAttribute("msg", "该书不在馆");
                model.addAttribute("book", book);
                return "user/book_lend";
            } else if (records.size()>=3){   //只能借3本书
                return "user/beyond";
            }else {
                book.setState(0);
                Record record = new Record();
                AllRecord allRecord = new AllRecord();
                Date date = new Date();
                record.setBookId(bookId);
                record.setUsername(username);
                record.setLendDate(date);
                record.setBackDate(new Date(date.getTime() + days * 86400000));
                allRecord.setBookId(bookId);
                allRecord.setUsername(username);
                allRecord.setLendDate(date);
                allRecord.setBackDate(new Date(date.getTime() + days * 86400000));
                recordService.insertRecord(record);   //插入借阅记录
                allRecordService.insertRecord(allRecord);  //历史记录
                bookService.updateBook(book);
                return "redirect:/reader/record/records/" + username;
            }
        }
        return "user/error";
    }





    //借阅记录
    @RequestMapping("/reader/record/records/{username}")
    public String records(@PathVariable("username") String username, Model model) {
        List<Record> records = allRecordService.selectRecordByUsername(username);
        model.addAttribute("records", records);
        return "user/ownRecord";
    }



    //还书
    @RequestMapping("/reader/record/backBook/{sernum}/{bookId}/{username}")
    public String backBook(@PathVariable("sernum") int sernum, @PathVariable("bookId") int bookId, @PathVariable("username") String username, Model model) {
        Book book = bookService.selectBookById(bookId);
        book.setState(1);  //状态设置为1，即为还书
        bookService.updateBook(book);
        model.addAttribute("msg","还书成功");
//        recordService.delectRecord(sernum);  //删除借阅记录
        return "redirect:/reader/record/records/" + username;
    }

    //搜索图书信息
    @RequestMapping("/reader/book/searchBook")
    public String searchBook(String keyword, Model model,@RequestParam(defaultValue = "1")Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        String key = "%" + keyword + "%";
        List<Book> books = bookService.selectLike(key);
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("books", books);
        return "user/books";
    }

    //到达修改密码界面
    @RequestMapping("/reader/pwd/toChangePassword")
    public String tochangePassword() {
        return "user/password";
    }


    //修改密码
    @RequestMapping("/reader/pwd/changePassword/{username}")
    public String changePassword(String checkPassword, @PathVariable("username") String username, Model model) {
        Reader reader = readerService.selectReaderByUsername(username);
        reader.setPassword(checkPassword);
        readerService.updateReader(reader);
        model.addAttribute("msg", "密码修改成功");
        return "user/password";

    }

    //个人信息
    @RequestMapping("/reader/info/toReaderInfo/{username}")
    public String toReaderInfo(@PathVariable("username") String username, Model model) {
        Reader reader = readerService.selectReaderByUsername(username);
        model.addAttribute("reader", reader);
        return "user/reader_info";
    }


}

