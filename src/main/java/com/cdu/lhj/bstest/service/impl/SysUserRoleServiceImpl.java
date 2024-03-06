package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysUserRoleMapper;
import com.cdu.lhj.bstest.pojo.SysRole;
import com.cdu.lhj.bstest.pojo.SysUserRole;
import com.cdu.lhj.bstest.service.SysUserRoleService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    @Transactional
    public boolean saveUserRole(SysUserRole userRole) {
        userRole.setId(SimpleTimestampIdGenerator.nextId());
        return save(userRole);
    }

    @Override
    @Transactional
    public boolean updateUserRole(SysUserRole userRole) {
        return updateById(userRole);
    }

    @Override
    @Transactional
    public boolean deleteUserRole(Long userRoleId) {
        return removeById(userRoleId);
    }

    @Override
    public List<SysRole> getRoleByUserId(Long userId) {
        //调用mapper查询
        return getBaseMapper().getRoleByUserId(userId);
    }
}
