package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.cdu.lhj.bstest.pojo.Bo.*;
import com.cdu.lhj.bstest.pojo.SysUser;
import com.cdu.lhj.bstest.service.SendSms;
import com.cdu.lhj.bstest.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @Resource
    private SendSms sendSms;

    @PostMapping("/sendCode")
    @SaIgnore
    public SaResult sendCode(@RequestBody SmsBo smsBo) {
        if(StrUtil.isEmpty(smsBo.getPhoneNum())){
            return SaResult.error("手机号不能为空");
        }
        String code = RandomUtil.randomNumbers(6);
        try {
            sendSms.send(smsBo.getPhoneNum(), smsBo.getTemplateCode(), code, 1L);
            return SaResult.ok("发送成功");
        } catch (Exception e) {
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("/doLoginByPhoneCode")
    @SaIgnore
    public SaResult doLoginByPhoneCode(@RequestBody LoginSmsBo loginSmsBo) {
        // 判空
        if (StrUtil.isEmpty(loginSmsBo.getPhoneNum()) || StrUtil.isEmpty(loginSmsBo.getCode())) {
            return SaResult.error("手机号或者验证码不能为空");
        }
        // 进行登录
        try {
            SysUser sysUser = sysUserService.doLoginByPhoneCode(loginSmsBo);
            if (sysUser != null) {
                StpUtil.login(sysUser.getId());
                Map<Object, Object> map = new HashMap<>();
                map.put("token", StpUtil.getTokenValue());
                map.put("expire", StpUtil.getTokenTimeout()/86400);
                return SaResult.data(map);
            } else {
                return SaResult.error("登录失败，手机号或者验证码错误");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return SaResult.error("登录失败，手机号或者验证码错误");
        }
    }

    @PostMapping("/updatePhone")
    public SaResult updatePhone(@RequestBody UpdatePhoneBo updatePhoneBo){
        // 拿到id
        Long id = StpUtil.getLoginIdAsLong();
        updatePhoneBo.setId(id);
        try {
            return SaResult.data(sysUserService.updateUserPhone(updatePhoneBo));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("/updatePwd")
    public SaResult updatePwd(@RequestBody UpdatePwdBo updatePwdBo){
        // 拿到id
        Long id = StpUtil.getLoginIdAsLong();
        updatePwdBo.setId(id);
        try {
            return SaResult.data(sysUserService.updateUserPwd(updatePwdBo));
        }catch (Exception e){
            return SaResult.error(e.getMessage());
        }
    }

    @PostMapping("/doLogin")
    @SaIgnore
    public SaResult doLogin(@RequestBody UserBo userBo) {
        // 判空
        if (StrUtil.isEmpty(userBo.getName()) || StrUtil.isEmpty(userBo.getPwd())) {
            return SaResult.error("用户名或者密码不能为空");
        }
        // 进行登录
        try {
            SysUser sysUser = sysUserService.doLogin(userBo.getName(), SaSecureUtil.md5(userBo.getPwd()));

            if (sysUser != null) {
                StpUtil.login(sysUser.getId());
                Map<Object, Object> map = new HashMap<>();
                map.put("token", StpUtil.getTokenValue());
                map.put("expire", StpUtil.getTokenTimeout()/86400);
                return SaResult.data(map);
            } else {
                return SaResult.error("用户名或者密码错误");
            }
        } catch (Exception exception) {
            return SaResult.error(exception.getMessage());
        }
    }

    @PostMapping("/insert")
    @SaIgnore
    public SaResult insert(@RequestBody SysUser user) {
        // 进行判空操作
        if (StrUtil.isEmpty(user.getUsername()) || StrUtil.isEmpty(user.getPassword())) {
            return SaResult.error("参数不能为空");
        }
        if (sysUserService.getUserByName(user.getUsername()) != null) {
            return SaResult.error("用户名已存在");
        }
        if(sysUserService.saveUser(user)){
            StpUtil.login(user.getId());
            Map<Object, Object> map = new HashMap<>();
            map.put("token", StpUtil.getTokenValue());
            map.put("expire", StpUtil.getTokenTimeout()/86400);
            return SaResult.data(map);
        }else {
            return SaResult.error("添加失败");
        }
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @PostMapping("/delete")
    public SaResult delete(@RequestParam Long id) {
        // 拿到当前登录用户的id
        return SaResult.data(sysUserService.deleteUser(id));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @PostMapping("/update")
    public SaResult update(@RequestBody SysUser user) {
        // 如果说name没有更改，那么久改为空免得出现重复
        if (sysUserService.getUserByName(user.getUsername()) != null) {
            user.setUsername(null);
        }
        if (sysUserService.getUserByPhone(user.getPhone()) != null) {
            user.setUsername(null);
        }
        return SaResult.data(sysUserService.updateUser(user));
    }

    @GetMapping("/getUser")
    public SaResult get() {
        // 拿到当前登录用户的id
        Long id = StpUtil.getLoginIdAsLong();
        return SaResult.data(sysUserService.getUserById(id));
    }

    @SaCheckPermission(value = {"admin"}, orRole = "super-admin")
    @PostMapping("/list")
    public SaResult list(@RequestBody UserSearchBo userSearchBo) {
        if(userSearchBo.getPage() == null || userSearchBo.getPage() < 1){
            userSearchBo.setPage(1);
        }
        if (userSearchBo.getSize() == null || userSearchBo.getSize() < 1){
            userSearchBo.setSize(10);
        }
        return SaResult.data(sysUserService.listUsers(userSearchBo));
    }
}
