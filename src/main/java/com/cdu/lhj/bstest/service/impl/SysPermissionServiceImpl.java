package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysPermissionMapper;
import com.cdu.lhj.bstest.pojo.SysPermission;
import com.cdu.lhj.bstest.service.SysPermissionService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {


    @Override
    @Transactional
    public boolean savePermission(SysPermission permission) {
        permission.setId(SimpleTimestampIdGenerator.nextId());
        return save(permission);
    }

    @Override
    @Transactional
    public boolean updatePermission(SysPermission permission) {
        return updateById(permission);
    }

    @Override
    @Transactional
    public boolean deletePermission(Long permissionId) {
        return removeById(permissionId);
    }

    @Override
    public SysPermission getPermissionById(Long permissionId) {
        return getById(permissionId);
    }

    @Override
    public List<SysPermission> listPermissions(Integer page, Integer size) {
        Page<SysPermission> sysPermissionPage = new Page<>(page, size);
        return page(sysPermissionPage).getRecords();
    }
}
