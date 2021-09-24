package com.niluogege.myspringboot.service;

import com.niluogege.myspringboot.mapper.DemoMapper;
import com.niluogege.myspringboot.model.entity.MybatisLearn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;


    /**
     * 插入全部字段
     */
    public void insert(MybatisLearn record) {
        demoMapper.insert(record);
    }
}
