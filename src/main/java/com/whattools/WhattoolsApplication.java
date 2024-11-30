package com.whattools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whattools.mapper")
public class WhattoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhattoolsApplication.class, args);
    }

}
