package com.zjh.controller;

import com.zjh.pojo.Book;
import com.zjh.pojo.Reader;
import com.zjh.pojo.Record;
import com.zjh.pojo.User;
import com.zjh.service.BookService;
import com.zjh.service.ReaderService;
import com.zjh.service.RecordService;
import com.zjh.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-24 13:41
 */
@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    ReaderService readerService;

    @Autowired
    RecordService recordService;

    @Autowired
    BookService bookService;

    //图书管理
    @RequestMapping("/admin/book/books")
    public String books(Model model) {
        List<Book> books = bookService.selectAllBook();
        model.addAttribute("books", books);
        return "admin/books";
    }

    //到达添加图书界面
    @RequestMapping("/admin/book/toAdd")
    public String toAdd(Model model) {
        return "admin/book_add";
    }

    //添加图书
    @PostMapping("/admin/book/add")
    public String addBook(Book book) {
        bookService.insertBook(book);
        return "redirect:/admin/book/books";
    }


    //图书详情
    @RequestMapping("/admin/book/info/{bookId}")
    public String info(@PathVariable("bookId") int bookId, Model model) {
        Book book = bookService.selectBookById(bookId);
        model.addAttribute("book", book);
        return "admin/book_info";
    }

    //到达图书编辑页面
    @RequestMapping("/admin/book/toUpdate/{bookId}")
    public String toUpdate(@PathVariable("bookId") int bookId, Model model) {
        Book book = bookService.selectBookById(bookId);
        model.addAttribute("book", book);
        return "admin/book_edit";
    }

    //更改图书信息
    @PostMapping("/admin/book/update")
    public String update(Book book) {
        bookService.updateBook(book);
        return "redirect:/admin/book/books";
    }

    //删除图书
    @RequestMapping("/admin/book/delete/{bookId}")
    public String delete(@PathVariable("bookId") int bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/admin/book/books";
    }

    //读者管理
    @RequestMapping("/admin/reader/readers")
    public String reader(Model model) {
        List<Reader> readers = readerService.selectAllReader();
        model.addAttribute("readers", readers);
        return "admin/readers";
    }

    //到达添加读者信息界面
    @RequestMapping("/admin/reader/toAdd")
    public String toAddReader(Model model) {
        return "admin/reader_add";
    }

    //添加读者信息
    @PostMapping("/admin/reader/add")
    public String add(Reader reader) {
        readerService.insertReader(reader);
        return "redirect:/admin/reader/readers";
    }

    //更改图书状态
    @RequestMapping("/admin/reader/state/{bookId}/{state}")
    public String state(@PathVariable("bookId") int bookId, @PathVariable("state") String state) {
        readerService.updateReaderState(bookId, state);
        return "redirect:/admin/reader/readers";
    }

    //借还管理
    @RequestMapping("/admin/record/records")
    public String records(Model model) {
        List<Record> records = recordService.selectAllRecord();
        model.addAttribute("records", records);
        return "admin/records";
    }

    //到达修改读者信息页面
    @RequestMapping("/admin/reader/toUpdateReader/{username}")
    public String toUpdate(@PathVariable("username") String username, Model model) {
        Reader reader = readerService.selectReaderByUsername(username);
        model.addAttribute("reader", reader);
        return "admin/reader_info";

    }

    //修改读者信息
    @RequestMapping("/admin/reader/update")
    public String updateReader(Reader reader, Model model) {
        readerService.updateReader(reader);
        Reader reader1 = readerService.selectReaderByUsername(reader.getUsername());
        model.addAttribute("msg", "修改成功");
        model.addAttribute("reader", reader);
        return "admin/reader_info";
    }

    //搜索图书信息
    @RequestMapping("/admin/book/searchBook")
    public String searchBook(String keyword, Model model) {
        String key = "%" + keyword + "%";
        List<Book> books = bookService.selectLike(key);
        model.addAttribute("books", books);
        return "admin/books";
    }

    //搜索读者信息
    @RequestMapping("/reader/book/searchReader")
    public String searchReader(String keyword, Model model) {
        String key = "%" + keyword + "%";
        List<Reader> readers = readerService.selectLike(key);
        model.addAttribute("readers", readers);
        return "admin/readers";
    }

    //到达修改密码页面
    @RequestMapping("/admin/user/toChangePassword")
    public String toChangePassword() {
        return "admin/password";
    }


    //异步检查密码
    @RequestMapping("/admin/user/checkPassword")
    @ResponseBody
    public String check(String pwd) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String username = (String) session.getAttribute("username");
        User user = userService.selectUserByUsername(username);
        if (user.getPassword().equals(pwd)) {
            return "true";
        } else {
            return "false";
        }
    }

    //修改密码
    @RequestMapping("/admin/user/changePassword/{username}")
    public String changePassword(String checkPassword, @PathVariable("username") String username, Model model) {
        User user = userService.selectUserByUsername(username);
        user.setPassword(checkPassword);
        userService.updateUserByUsername(user);
        model.addAttribute("msg", "修改成功");
        return "admin/password";
    }


}
