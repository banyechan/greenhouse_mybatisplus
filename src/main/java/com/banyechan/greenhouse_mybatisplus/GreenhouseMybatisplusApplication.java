package com.banyechan.greenhouse_mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.banyechan.greenhouse_mybatisplus.mapper")
public class GreenhouseMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenhouseMybatisplusApplication.class, args);
    }

}
