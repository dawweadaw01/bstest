package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.SysPermission;
import com.cdu.lhj.bstest.pojo.SysRolePermission;

import java.util.List;

public interface SysRolePermissionService extends IService<SysRolePermission> {

    List<SysPermission> getPermissionByRoleId(Long roleId);

    boolean saveRolePermission(SysRolePermission rolePermission);

    List<SysPermission> getPermissionListForManager(Long roleId);
}
