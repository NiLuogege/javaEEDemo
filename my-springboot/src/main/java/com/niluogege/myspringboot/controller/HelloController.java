package com.niluogege.myspringboot.controller;

import com.niluogege.myspringboot.model.entity.MybatisLearn;
import com.niluogege.myspringboot.service.MybatisLearnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Api(value = "desc of class")
@RestController
public class HelloController {

    @Resource
    private MybatisLearnService demoService;

    @ApiOperation(value = "desc of method", notes = "")
    @RequestMapping(value = "/index")
    public String index() {
        return "fuck android";
    }

    @ApiOperation(value = "desc of method", notes = "")
    @RequestMapping(value = "/addDemo")
    public String addDemo() {
        MybatisLearn demo = new MybatisLearn();
        demo.setName("aa");
        demo.setCreatetime(LocalDateTime.now());
        demo.setAge(1);
        demoService.insert(demo);
        return "ok";
    }

    @ApiOperation(value = "desc of method", notes = "")
    @RequestMapping(value = "/getAll")
    public String getAll() {
        return demoService.getAll().toString();
    }
}
