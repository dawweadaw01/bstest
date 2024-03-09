package com.cdu.lhj.bstest.config;

import cn.dev33.satoken.stp.StpInterface;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.service.SysRolePermissionService;
import com.cdu.lhj.bstest.service.SysUserRoleService;
import com.cdu.lhj.bstest.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StpInterfaceConfig implements StpInterface {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 转换为long类型
        Long id = Long.valueOf((String) loginId);
        List<SysRole> roles = sysUserRoleService.getRoleByUserId(id);
        // 遍历角色，获取权限标识符
        Set<String> set = new HashSet<>();
        for (SysRole role : roles) {
            sysRolePermissionService.getPermissionByRoleId(role.getId()).forEach(permission -> set.add(permission.getName()));
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Long id = Long.valueOf((String) loginId);
        // 根据账号id查询角色
        List<SysRole> roles = sysUserRoleService.getRoleByUserId(id);
        // 返回角色标识符集合
        List<String> list = new ArrayList<>();
        // 换成lambda表达式
        roles.forEach(role -> list.add(role.getName()));
        return list;
    }
}
