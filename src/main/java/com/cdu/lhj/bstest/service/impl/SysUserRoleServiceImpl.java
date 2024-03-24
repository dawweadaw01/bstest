package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysUserRoleMapper;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.pojo.SysUserRole;
import com.cdu.lhj.bstest.pojo.Vo.SysUserRoleVo;
import com.cdu.lhj.bstest.service.SysUserRoleService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    @Transactional
    @CacheEvict(value = "userRole", allEntries = true)
    public boolean saveUserRole(SysUserRole userRole) {
        userRole.setId(SimpleTimestampIdGenerator.nextId());
        LambdaQueryWrapper<SysUserRole> eq = new LambdaQueryWrapper<SysUserRole>().
                eq(SysUserRole::getUserId, userRole.getUserId()).eq(SysUserRole::getRoleId, userRole.getRoleId());
        SysUserRole one = getOne(eq);
        if(one != null){
            return false;
        }
        return save(userRole);
    }

    @Override
    @Transactional
    @CacheEvict(value = "userRole", allEntries = true)
    public boolean updateUserRole(SysUserRole userRole) {
        return updateById(userRole);
    }

    @Override
    @Transactional
    @CacheEvict(value = "userRole", allEntries = true)
    public boolean deleteUserRole(Long userRoleId) {
        return removeById(userRoleId);
    }

    @Override
    @Cacheable(value = "userRole", key = "#userId")
    public List<SysRole> getRoleByUserId(Long userId) {
        //调用mapper查询
        return getBaseMapper().getRoleByUserId(userId);
    }

    @Override
    public List<String> getRoleByUser(Long id) {
        List<SysRole> roles = getBaseMapper().getRoleByUserId(id);
        List<String> strings = new ArrayList<>();
        for (SysRole role : roles) {
            strings.add(role.getName());
        }
        return strings;
    }

    @Override
    public List<SysUserRoleVo> getByUserFormManager(Long userId) {
        return getBaseMapper().getByUserFormManager(userId);

    }
}
