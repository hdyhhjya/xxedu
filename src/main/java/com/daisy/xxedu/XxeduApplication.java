package com.daisy.xxedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.daisy.xxedu.mapper")
public class XxeduApplication {

    public static void main(String[] args) {
        SpringApplication.run(XxeduApplication.class, args);
    }

}
