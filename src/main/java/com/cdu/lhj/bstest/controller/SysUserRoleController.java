package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.SysUserRole;
import com.cdu.lhj.bstest.service.SysRoleService;
import com.cdu.lhj.bstest.service.SysUserRoleService;
import com.cdu.lhj.bstest.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@SaCheckPermission(value = {"admin"}, orRole = "super-admin")
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/insert")
    public SaResult insert(@RequestBody SysUserRole userRole) {
        // 进行判空操作
        if (userRole.getUserId() == null || userRole.getRoleId() == null) {
            return SaResult.error("参数不能为空");
        }
        // 查询判断用户是否存在，角色是否存在
        if (sysUserService.getById(userRole.getUserId()) == null || sysRoleService.getById(userRole.getRoleId()) == null) {
            return SaResult.error("用户或角色不存在");
        }
        return SaResult.data(sysUserRoleService.saveUserRole(userRole));
    }

    @PostMapping("/delete")
    public SaResult delete(@RequestParam Long userRoleId) {
        // 进行判空操作
        if (userRoleId == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysUserRoleService.deleteUserRole(userRoleId));
    }

    @PostMapping("/update")
    public SaResult update(@RequestBody SysUserRole userRole) {
        // 进行判空操作
        if (userRole.getUserId() == null || userRole.getRoleId() == null) {
            return SaResult.error("参数不能为空");
        }
        // 查询判断用户是否存在，角色是否存在
        if (userRole.getId() == null || sysUserService.getById(userRole.getUserId()) == null || sysRoleService.getById(userRole.getRoleId()) == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysUserRoleService.updateUserRole(userRole));
    }

    @GetMapping("/getByUserId")
    public SaResult getRoleByUserId(@RequestParam Long userId) {
        // 进行判空操作
        if (userId == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysUserRoleService.getRoleByUserId(userId));
    }

}
