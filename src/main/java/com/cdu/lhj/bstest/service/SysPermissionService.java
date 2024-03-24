package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Bo.PermissionSearchBo;
import com.cdu.lhj.bstest.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {

    boolean savePermission(SysPermission permission);

    boolean updatePermission(SysPermission permission);

    boolean deletePermission(Long permissionId);

    SysPermission getPermissionById(Long permissionId);

    List<SysPermission> listPermissions();


    IPage<SysPermission> getPermissionBySearch(PermissionSearchBo permissionSearchBo);
}
