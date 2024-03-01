package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.TestMapper;
import com.cdu.lhj.bstest.pojo.Test;
import com.cdu.lhj.bstest.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Override
    public String test() {
        // 全查
        return this.list().toString();
    }
}
