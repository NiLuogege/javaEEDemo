package com.niluogege.myspringboot.mapper;

import com.niluogege.myspringboot.model.entity.MybatisLearn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MybatisLearnMapper {

    @Insert("insert into mybatis_learn(name,age,createtime)values(#{name},#{age},#{createtime})")
    void insert(MybatisLearn demo);

    @Select("select * from mybatis_learn")
    List<MybatisLearn> getAll();
}
