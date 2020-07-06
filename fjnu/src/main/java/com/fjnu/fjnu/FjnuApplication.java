package com.fjnu.fjnu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fjnu.fjnu.mapper")
public class FjnuApplication {

    public static void main(String[] args) {
        SpringApplication.run(FjnuApplication.class, args);
    }

}
