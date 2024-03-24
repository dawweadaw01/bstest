package com.cdu.lhj.bstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
//在启动类添加这个逐渐 这个是暴露代理对象
@EnableAspectJAutoProxy(exposeProxy = true)
public class BstestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BstestApplication.class, args);
    }

}
