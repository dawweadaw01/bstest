package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Test;
import org.springframework.stereotype.Service;

@Service
public interface TestService extends IService<Test> {
    public String test();
}
