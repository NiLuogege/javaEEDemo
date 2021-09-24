package com.niluogege.myspringboot.controller;

import com.niluogege.myspringboot.service.DemoService;
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
    private DemoService demoService;

    @ApiOperation(value = "desc of method", notes = "")
    @RequestMapping(value = "/index")
    public String index(){
        return "fuck android";
    }

    @ApiOperation(value = "desc of method", notes = "")
    @RequestMapping(value = "/addDemo")
    public String addDemo(){
//        Demo demo = new Demo();
//        demo.setName("aa");
//        demo.setDate(LocalDateTime.now());
//        return demoService.insert(demo)+"";
        return "";
    }
}
