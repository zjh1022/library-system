package com.zjh.service.impl;

import com.zjh.mapper.RecordMapper;
import com.zjh.pojo.Record;
import com.zjh.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:56
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public int insertRecord(Record record) {
        return recordMapper.insertRecord(record);
    }

    @Override
    public List<Record> selectAllRecord() {
        return recordMapper.selectAllRecord();
    }

    @Override
    public List<Record> selectRecordByUsername(String username) {
        return recordMapper.selectRecordByUsername(username);
    }

    @Override
    public int delectRecord(int sernum) {
        return recordMapper.deleteReocrd(sernum);
    }
}
