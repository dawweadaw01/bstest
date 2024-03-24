package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysRoleMapper;
import com.cdu.lhj.bstest.pojo.Bo.RoleSearchBo;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.pojo.Vo.RoleSearchVo;
import com.cdu.lhj.bstest.service.SysRolePermissionService;
import com.cdu.lhj.bstest.service.SysRoleService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Override
    @CacheEvict(value = "role", allEntries = true)
    public boolean saveRole(SysRole role) {
        role.setId(SimpleTimestampIdGenerator.nextId());
        return save(role);
    }

    @Override
    @CacheEvict(value = "role", allEntries = true)
    public boolean updateRole(SysRole role) {
        return updateById(role);
    }

    @Override
    @CacheEvict(value = "role", allEntries = true)
    public boolean deleteRole(Long roleId) {
        return removeById(roleId);
    }

    @Override
    @Cacheable(value = "role", key = "#roleId")
    public SysRole getRoleById(Long roleId) {
        return getById(roleId);
    }

    @Override
    @Cacheable(value = "role")
    public List<SysRole> listRoles() {
        // 不分页
        return list();
    }

    @Override
    public IPage<SysRole> getRoleBySearch(RoleSearchBo roleSearchBo) {
        // 判断分页参数是否为空
        if (roleSearchBo.getPage() == null || roleSearchBo.getPage() < 1) {
            roleSearchBo.setPage(1);
        }
        if (roleSearchBo.getSize() == null || roleSearchBo.getSize() < 1) {
            roleSearchBo.setSize(10);
        }
        Page<SysRole> roleSearchVoPage = new Page<>(roleSearchBo.getPage(), roleSearchBo.getSize());
        // 直接分页查询
        IPage<SysRole> page = this.baseMapper.getRoleBySearch(roleSearchVoPage, roleSearchBo);
        page.getRecords().forEach(sysRole -> {
            // 设置权限列表
            StringBuffer permissions = new StringBuffer();
            sysRolePermissionService.getPermissionByRoleId(sysRole.getId()).forEach(sysPermission -> {
                permissions.append("-").append(sysPermission.getName());
            });
            if (!permissions.isEmpty()) {
                sysRole.setPermissions(permissions.substring(1));
            } else {
                sysRole.setPermissions("");
            }
        });
        return page;
    }
}
