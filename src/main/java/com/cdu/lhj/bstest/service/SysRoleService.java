package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Bo.RoleSearchBo;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.pojo.Vo.RoleSearchVo;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    boolean saveRole(SysRole role);

    boolean updateRole(SysRole role);

    boolean deleteRole(Long roleId);

    SysRole getRoleById(Long roleId);

    List<SysRole> listRoles();

    IPage<SysRole> getRoleBySearch(RoleSearchBo roleSearchBo);
}
