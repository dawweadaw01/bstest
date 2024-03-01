package com.cdu.lhj.bstest.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.User;
import com.cdu.lhj.bstest.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("test")
    public String test() {
        return testService.test();
    }

    @RequestMapping("testSatoken")
    public String testSatoken() {
        System.out.println(123);
        StpUtil.login(10001);
        return StpUtil.getPermissionList().toString();
    }

    // 会话登录接口
    @RequestMapping(value = "doLogin", produces = "application/json")
    public SaResult doLogin(@RequestBody User user) {
        // 第一步：比对前端提交的账号名称、密码
        if ("zhang".equals(user.getName()) && "123456".equals(user.getPwd())) {
            // 第二步：根据账号id，进行登录
            StpUtil.login(10001);
            return SaResult.ok(StpUtil.getTokenValue());
        }
        return SaResult.error("登录失败");
    }

    @RequestMapping(value = "test", produces = "application/json")
    public SaResult test(@RequestHeader(value = "satoken", required = false) String token) {
        Object loginIdByToken = StpUtil.getLoginIdByToken(token);
        return SaResult.data(loginIdByToken);
    }
}
