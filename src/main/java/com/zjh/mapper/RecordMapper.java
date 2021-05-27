package com.zjh.mapper;


import com.zjh.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {
    //插入一条借阅记录
    int insertRecord(Record record);

    //根据读者卡号查询借阅记录
    List<Record> selectRecordByUsername(@Param("username") String username);

    //根据流水号删除借阅记录
    int deleteReocrd(@Param("sernum") int sernum);

    //查询所有借阅记录
    List<Record> selectAllRecord();
}
