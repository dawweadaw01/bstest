package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    boolean saveRole(SysRole role);

    boolean updateRole(SysRole role);

    boolean deleteRole(Long roleId);

    SysRole getRoleById(Long roleId);

    IPage<SysRole> listRoles(Integer page, Integer size);

}
