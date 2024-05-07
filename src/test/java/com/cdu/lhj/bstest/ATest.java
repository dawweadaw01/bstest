package com.cdu.lhj.bstest;


import com.cdu.lhj.bstest.service.ShopsService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ATest {
    @Resource
    private ShopsService shopsService;

    @Test
    public void test(){
        long loginIdAsLong = 25662670247936L;
    }
}
