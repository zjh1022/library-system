package com.zjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @auther zheng jianghai
 * @create 2021-05-21 19:51
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

     private int bookId;//图书号
     private String name;//图书名
     private String author;//作者
     private String publish;//出版社
     private String isbn;//IDBN
     private String introduction;//图书简介
     private BigDecimal price;//图书价格
     private int state;//借出状态

}


