package com.niluogege.myspringboot.service;

import com.niluogege.myspringboot.mapper.MybatisLearnMapper;
import com.niluogege.myspringboot.model.entity.MybatisLearn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MybatisLearnService {

    @Resource
    private MybatisLearnMapper demoMapper;


    /**
     * 插入全部字段
     */
    public void insert(MybatisLearn record) {
        demoMapper.insert(record);
    }

    /**
     * 获取全部数据
     */
    public  List<MybatisLearn> getAll(){
        return demoMapper.getAll();
    }
}
