package com.cdu.lhj.bstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BstestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BstestApplication.class, args);
    }

}
