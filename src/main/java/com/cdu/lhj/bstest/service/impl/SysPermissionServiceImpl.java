package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysPermissionMapper;
import com.cdu.lhj.bstest.pojo.Bo.PermissionSearchBo;
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
        LambdaQueryWrapper<SysPermission> eq = new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getName, permission.getName());
        if(getOne(eq) != null){
            return false;
        }
        return save(permission);
    }

    @Override
    @Transactional
    @CacheEvict(value = "permissions", allEntries = true)
    public boolean updatePermission(SysPermission permission) {
        if(permission.getName() != null){
            LambdaQueryWrapper<SysPermission> eq = new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getName, permission.getName());
            if(getOne(eq) != null){
                permission.setName(null);
            }
        }
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
    @Cacheable(value = "permissions")
    public List<SysPermission> listPermissions() {
        return list();
    }

    @Override
    public IPage<SysPermission> getPermissionBySearch(PermissionSearchBo permissionSearchBo) {
        Page<SysPermission> page = new Page<>(permissionSearchBo.getPage(), permissionSearchBo.getSize());
        LambdaQueryWrapper<SysPermission> eq = new LambdaQueryWrapper<SysPermission>()
                .like(permissionSearchBo.getName() != null, SysPermission::getName, permissionSearchBo.getName())
                .like(permissionSearchBo.getDescription() != null, SysPermission::getDescription, permissionSearchBo.getDescription());
        return page(page, eq);
    }
}
