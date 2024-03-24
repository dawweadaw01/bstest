package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysRolePermissionMapper;
import com.cdu.lhj.bstest.pojo.SysPermission;
import com.cdu.lhj.bstest.pojo.SysRolePermission;
import com.cdu.lhj.bstest.service.SysRolePermissionService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {


    @Override
    @Cacheable(value = "rolePermission", key = "#roleId")
    public List<SysPermission> getPermissionByRoleId(Long roleId) {
        //调用mapper查询
        return getBaseMapper().getPermissionByRoleId(roleId);
    }

    @Override
    @CacheEvict(value = "rolePermission", allEntries = true)
    public boolean saveRolePermission(SysRolePermission rolePermission) {
        rolePermission.setId(SimpleTimestampIdGenerator.nextId());
        LambdaQueryWrapper<SysRolePermission> eq = new LambdaQueryWrapper<SysRolePermission>().
                eq(SysRolePermission::getRoleId, rolePermission.getRoleId()).
                eq(SysRolePermission::getPermissionId, rolePermission.getPermissionId());
        if (getOne(eq) != null) {
            return false;
        }
        return save(rolePermission);
    }

    @Override
    public List<SysPermission> getPermissionListForManager(Long roleId) {
        return this.baseMapper.getPermissionListForManager(roleId);
    }

}
