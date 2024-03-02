package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.pojo.SysUserRole;

import java.util.List;


public interface SysUserRoleService extends IService<SysUserRole> {

    boolean saveUserRole(SysUserRole userRole);

    boolean updateUserRole(SysUserRole userRole);

    boolean deleteUserRole(Long userRoleId);

    List<SysRole> getRoleByUserId(Long userId);
}
