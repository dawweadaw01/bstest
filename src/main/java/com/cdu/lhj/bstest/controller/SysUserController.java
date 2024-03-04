package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.StrUtil;
import com.cdu.lhj.bstest.pojo.SysUser;
import com.cdu.lhj.bstest.pojo.User;
import com.cdu.lhj.bstest.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @PostMapping("/doLogin")
    public SaResult doLogin(@RequestBody User user) {
        // 判空
        if (StrUtil.isEmpty(user.getName()) || StrUtil.isEmpty(user.getPwd())) {
            return SaResult.error("参数不能为空");
        }
        // 进行登录
        try {
            SysUser sysUser = sysUserService.doLogin(user.getName(),SaSecureUtil.md5(user.getPwd()));
            if (sysUser != null) {
                StpUtil.login(sysUser.getId());
                return SaResult.data(StpUtil.getTokenValue());
            } else {
                return SaResult.error("用户名或者密码错误");
            }
        } catch (Exception exception) {
            return SaResult.error(exception.getMessage());
        }
    }

    @PostMapping("/insert")
    public SaResult insert(@RequestBody SysUser user) {
        // 进行判空操作
        if (StrUtil.isEmpty(user.getUsername()) || StrUtil.isEmpty(user.getPassword())) {
            return SaResult.error("参数不能为空");
        }
        if(sysUserService.getUserByName(user.getUsername()) != null) {
            return SaResult.error("用户名已存在");
        }
        return SaResult.data(sysUserService.saveUser(user));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @PostMapping("/delete")
    public SaResult delete() {
        // 拿到当前登录用户的id
        Long id = StpUtil.getLoginIdAsLong();
        return SaResult.data(sysUserService.deleteUser(id));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @PostMapping("/update")
    public SaResult update(@RequestBody SysUser user) {
        // 进行判空操作
        if (StrUtil.isEmpty(user.getUsername()) || StrUtil.isEmpty(user.getPassword())) {
            return SaResult.error("参数不能为空");
        }
        user.setId(StpUtil.getLoginIdAsLong());
        return SaResult.data(sysUserService.updateUser(user));
    }

    @GetMapping("/getUser")
    public SaResult get() {
        // 拿到当前登录用户的id
        Long id = StpUtil.getLoginIdAsLong();
        return SaResult.data(sysUserService.getUserById(id));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @GetMapping("/list")
    public SaResult list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        return SaResult.data(sysUserService.listUsers(page, size));
    }
}
