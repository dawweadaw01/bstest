package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import com.cdu.lhj.bstest.pojo.SysRolePermission;
import com.cdu.lhj.bstest.service.SysPermissionService;
import com.cdu.lhj.bstest.service.SysRolePermissionService;
import com.cdu.lhj.bstest.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sysRolePermission")
@SaCheckPermission(value = {"admin"}, orRole = "super-admin")
public class SysRolePermissionController {

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysPermissionService sysPermissionService;

    @PostMapping("/insert")
    public SaResult insert(@RequestBody SysRolePermission rolePermission) {
        // 进行判空操作
        if (rolePermission.getRoleId() == null || rolePermission.getPermissionId() == null) {
            return SaResult.error("参数不能为空");
        }
        // 判断角色和权限是否存在
        if (sysRoleService.getById(rolePermission.getRoleId()) == null || sysPermissionService.getById(rolePermission.getPermissionId()) == null) {
            return SaResult.error("角色或权限不存在");
        }
        return SaResult.data(sysRolePermissionService.saveRolePermission(rolePermission));
    }

    @PostMapping("/delete")
    public SaResult delete(Long rolePermissionId) {
        // 进行判空操作
        if (rolePermissionId == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysRolePermissionService.removeById(rolePermissionId));
    }

    @PostMapping("/update")
    public SaResult update(@RequestBody SysRolePermission rolePermission) {
        // 进行判空操作
        if (rolePermission.getId() == null || rolePermission.getRoleId() == null || rolePermission.getPermissionId() == null) {
            return SaResult.error("参数不能为空");
        }
        // 判断角色和权限是否存在
        if (sysRoleService.getById(rolePermission.getRoleId()) == null || sysPermissionService.getById(rolePermission.getPermissionId()) == null) {
            return SaResult.error("角色或权限不存在");
        }
        return SaResult.data(sysRolePermissionService.updateById(rolePermission));
    }

    @GetMapping("/getPermissionByRoleId")
    public SaResult getPermissionByRoleId(@RequestParam Long roleId) {
        // 进行判空操作
        if (roleId == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysRolePermissionService.getPermissionByRoleId(roleId));
    }

}
