package com.zjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auther zheng jianghai
 * @create 2021-05-21 19:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {
    private int readerId; //读者编号；
    private String username; //读者卡号
    private String password;
    private String name;  //读者姓名
    private String sex;  //读者性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth; //读者生日
    private String address; //读者地址
    private String telcode; //电话
    private int state;  //状态
    private String role;  //角色
}
