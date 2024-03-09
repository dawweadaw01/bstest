package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysRoleMapper;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.service.SysRoleService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    @CacheEvict(value = "roles", allEntries = true)
    public boolean saveRole(SysRole role) {
        role.setId(SimpleTimestampIdGenerator.nextId());
        return save(role);
    }

    @Override
    @CacheEvict(value = "roles", allEntries = true)
    public boolean updateRole(SysRole role) {
        return updateById(role);
    }

    @Override
    @CacheEvict(value = "roles", allEntries = true)
    public boolean deleteRole(Long roleId) {
        return removeById(roleId);
    }

    @Override
    @Cacheable(value = "roles", key = "#roleId")
    public SysRole getRoleById(Long roleId) {
        return getById(roleId);
    }

    @Override
    @Cacheable(value = "roles", key = "#page + '-' + #size")
    public List<SysRole> listRoles(Integer page, Integer size) {
        Page<SysRole> sysRolePage = new Page<>(page, size);
        return page(sysRolePage).getRecords();
    }

}
