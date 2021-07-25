package com.zjh.service.impl;

import com.zjh.mapper.AllRecordMapper;
import com.zjh.mapper.RecordMapper;
import com.zjh.pojo.AllRecord;
import com.zjh.pojo.Record;
import com.zjh.service.AllRecordService;
import com.zjh.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:56
 */
@Service
public class AllRecordServiceImpl implements AllRecordService {
    @Autowired
    AllRecordMapper allRecordMapper;

    @Override
    public int insertRecord(AllRecord allRecord) {
        return allRecordMapper.insertRecord(allRecord);
    }

    @Override
    public List<Record> selectAllRecord() {
        return allRecordMapper.selectAllRecord();
    }

    @Override
    public List<Record> selectRecordByUsername(String username) {
        return allRecordMapper.selectRecordByUsername(username);
    }

    @Override
    public int delectRecord(int sernum) {
        return allRecordMapper.deleteReocrd(sernum);
    }
}
