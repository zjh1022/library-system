package com.zjh.service;

import com.zjh.pojo.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:46
 */


public interface RecordService {
    //插入借阅记录
    int insertRecord(Record record);

    //查询借阅记录
    List<Record> selectAllRecord();

    //查询读者卡号所有借阅记录
    List<Record> selectRecordByUsername(@Param("username") String username);

    //根据借阅号删除记录
    int delectRecord(@Param("sernum") int sernum);
}
