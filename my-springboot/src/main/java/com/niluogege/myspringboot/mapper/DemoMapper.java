package com.niluogege.myspringboot.mapper;

import com.niluogege.myspringboot.model.entity.MybatisLearn;
import org.apache.ibatis.annotations.Insert;

public interface DemoMapper {

    @Insert("insert into mybatis_learn(name,age,createtime)values(#{name},#{age},#{createtime})")
    void insert(MybatisLearn demo);

}
