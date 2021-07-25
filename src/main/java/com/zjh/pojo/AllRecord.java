package com.zjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther zheng jianghai
 * @create 2021-06-17 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllRecord {
    private String sernum;//借阅号
    private int bookId;//图书号
    private String username;//读者卡号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lendDate;//借出日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date backDate;//归还日期
}
