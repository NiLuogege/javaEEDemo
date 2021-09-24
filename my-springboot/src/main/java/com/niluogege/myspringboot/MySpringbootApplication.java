package com.niluogege.myspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
//开启swagger3文档
@EnableOpenApi
@SpringBootApplication
//指定mapper文件夹，防止全量扫描
@MapperScan("com.niluogege.myspringboot.mapper")
public class MySpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringbootApplication.class, args);
    }

}
