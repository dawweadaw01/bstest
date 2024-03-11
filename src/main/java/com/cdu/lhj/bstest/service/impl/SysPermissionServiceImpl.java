package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysPermissionMapper;
import com.cdu.lhj.bstest.pojo.SysPermission;
import com.cdu.lhj.bstest.service.SysPermissionService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {


    @Override
    @Transactional
    @CacheEvict(value = "permissions", allEntries = true)
    public boolean savePermission(SysPermission permission) {
        permission.setId(SimpleTimestampIdGenerator.nextId());
        return save(permission);
    }

    @Override
    @Transactional
    @CacheEvict(value = "permissions", allEntries = true)
    public boolean updatePermission(SysPermission permission) {
        return updateById(permission);
    }

    @Override
    @Transactional
    @CacheEvict(value = "permissions", allEntries = true)
    public boolean deletePermission(Long permissionId) {
        return removeById(permissionId);
    }

    @Override
    @Cacheable(value = "permissions", key = "#permissionId")
    public SysPermission getPermissionById(Long permissionId) {
        return getById(permissionId);
    }

    @Override
    @Cacheable(value = "permissions", key = "#page + '-' + #size")
    public IPage<SysPermission> listPermissions(Integer page, Integer size) {
        Page<SysPermission> sysPermissionPage = new Page<>(page, size);
        return page(sysPermissionPage);
    }
}
