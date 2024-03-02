package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysRoleMapper;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public boolean saveRole(SysRole role) {
        return save(role);
    }

    @Override
    public boolean updateRole(SysRole role) {
        return updateById(role);
    }

    @Override
    public boolean deleteRole(Long roleId) {
        return removeById(roleId);
    }

    @Override
    public SysRole getRoleById(Long roleId) {
        return getById(roleId);
    }

    @Override
    public List<SysRole> listRoles(Integer page, Integer size) {
        Page<SysRole> sysRolePage = new Page<>(page, size);
        return page(sysRolePage).getRecords();
    }

}
