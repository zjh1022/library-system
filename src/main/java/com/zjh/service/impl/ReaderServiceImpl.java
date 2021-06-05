package com.zjh.service.impl;

import com.zjh.mapper.ReaderMapper;
import com.zjh.pojo.Reader;
import com.zjh.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zheng jianghai
 * @create 2021-05-22 19:53
 */
@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    ReaderMapper readerMapper;

    @Override
    public Reader selectReaderByUsername(String username) {
        return readerMapper.selectReaderByUsername(username);
    }

    @Override
    public List<Reader> selectAllReader() {
        return readerMapper.selectAllReader();
    }

    @Override
    public int insertReader(Reader reader) {
        return readerMapper.insertReader(reader);
    }

    @Override
    public int updateReaderState(int booKId, String state) {
        return readerMapper.updateReaderState(booKId, state);
    }

    @Override
    public Reader updateReader(Reader reader) {
        return readerMapper.updateReader(reader);
    }

    @Override
    public List<Reader> selectLike(String keywords) {
        return readerMapper.selectLike(keywords);
    }
}
