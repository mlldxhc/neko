package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.study.mapper")
public class Springboot3WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot3WebApplication.class, args);
    }

}
