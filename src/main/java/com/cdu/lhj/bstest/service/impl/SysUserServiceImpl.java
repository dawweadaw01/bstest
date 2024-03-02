package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysUserMapper;
import com.cdu.lhj.bstest.pojo.SysUser;
import com.cdu.lhj.bstest.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser doLogin(String username, String password) {
        LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username).eq(SysUser::getPassword, password);
        return getOne(eq);
    }

    @Override
    @Transactional
    public boolean saveUser(SysUser user) {
        return save(user);
    }

    @Override
    @Transactional
    public boolean updateUser(SysUser user) {
        return updateById(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(Long userId) {
        return removeById(userId);
    }

    @Override
    public SysUser getUserById(Long userId) {
        return getById(userId);
    }

    @Override
    public SysUser getUserByName(String username) {
        LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username);
        return getOne(eq);
    }

    @Override
    public List<SysUser> listUsers(Integer page, Integer size) {
        // 分页查询
        Page<SysUser> sysUserPage = new Page<>(page, size);
        return page(sysUserPage).getRecords();
    }

}
