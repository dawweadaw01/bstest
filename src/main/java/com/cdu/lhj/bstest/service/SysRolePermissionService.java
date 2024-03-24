package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Bo.RoleSearchBo;
import com.cdu.lhj.bstest.pojo.SysPermission;
import com.cdu.lhj.bstest.pojo.SysRolePermission;
import com.cdu.lhj.bstest.pojo.Vo.RoleSearchVo;

import java.util.List;

public interface SysRolePermissionService extends IService<SysRolePermission> {

    List<SysPermission> getPermissionByRoleId(Long roleId);

    boolean saveRolePermission(SysRolePermission rolePermission);

    List<SysPermission> getPermissionListForManager(Long roleId);
}
