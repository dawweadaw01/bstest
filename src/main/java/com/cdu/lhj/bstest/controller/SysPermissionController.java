package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.StrUtil;
import com.cdu.lhj.bstest.pojo.Bo.PermissionSearchBo;
import com.cdu.lhj.bstest.pojo.SysPermission;
import com.cdu.lhj.bstest.service.SysPermissionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@SaCheckPermission(value = {"admin"}, orRole = "super-admin")
@RequestMapping("/api/sysPermission")
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @PostMapping("/insert")
    public SaResult insert(@RequestBody SysPermission sysPermission) {

        // 进行判空操作
        if (StrUtil.isEmpty(sysPermission.getName())) {
            return SaResult.error("名字不能为空");
        }
        if(sysPermissionService.savePermission(sysPermission)){
            return SaResult.data(sysPermission);
        }else {
            return SaResult.error("权限名已存在");
        }
    }

    @PostMapping("/delete")
    public SaResult delete(Long permissionId) {
        // 进行判空操作
        if (permissionId == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysPermissionService.deletePermission(permissionId));
    }

    @PostMapping("/update")
    public SaResult update(@RequestBody SysPermission sysPermission) {
        // 进行判空操作
        if (sysPermission.getId() == null || StrUtil.isEmpty(sysPermission.getName())) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysPermissionService.updatePermission(sysPermission));
    }

    @PostMapping("/get")
    public SaResult get(Long permissionId) {
        // 进行判空操作
        if (permissionId == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysPermissionService.getPermissionById(permissionId));
    }

    @GetMapping("/list")
    public SaResult list() {
        // 进行判空操作
        return SaResult.data(sysPermissionService.listPermissions());
    }

    @PostMapping("/getPermissionBySearch")
    public SaResult getPermissionBySearch(@RequestBody PermissionSearchBo permissionSearchBo) {
        return SaResult.data(sysPermissionService.getPermissionBySearch(permissionSearchBo));
    }
}
