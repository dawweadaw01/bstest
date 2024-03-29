package com.cdu.lhj.bstest.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cdu.lhj.bstest.pojo.Bo.RoleSearchBo;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sysRole")
@SaCheckPermission(value = {"admin"}, orRole = "super-admin")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/insert")
    //@SaCheckPermission(value = {"admin"})
    @Transactional
    public SaResult insert(@RequestBody SysRole role) {
        System.out.println(StpUtil.getPermissionList());
        // 进行判空操作
        if (StrUtil.isEmpty(role.getName())) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysRoleService.saveRole(role));
    }

    @PostMapping(value = "/getRoleBySearch")
    public SaResult getRoleBySearch(@RequestBody RoleSearchBo roleSearchBo) {
        IPage<SysRole> searchVos =  sysRoleService.getRoleBySearch(roleSearchBo);
        return SaResult.data(searchVos);
    }

    @PostMapping("/delete")
    @Transactional
    public SaResult delete(@RequestParam Long roleId) {
        // 进行判空操作
        if (roleId == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysRoleService.deleteRole(roleId));
    }

    @PostMapping("/update")
    @Transactional
    public SaResult update(@RequestBody SysRole role) {
        // 进行判空操作
        if (role.getId() == null || StrUtil.isEmpty(role.getName())) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysRoleService.updateRole(role));
    }

    @GetMapping("/get")
    public SaResult get(@RequestParam Long roleId) {
        // 进行判空操作
        if (roleId == null) {
            return SaResult.error("参数不能为空");
        }
        return SaResult.data(sysRoleService.getRoleById(roleId));
    }

    @GetMapping("/list")
    public SaResult list() {
        return SaResult.data(sysRoleService.listRoles());
    }
}
