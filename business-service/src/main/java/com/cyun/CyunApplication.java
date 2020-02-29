package com.cyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.cyun.dao")
public class CyunApplication {
    public static void main(String[] args) {
        SpringApplication.run(CyunApplication.class, args);
    }
}