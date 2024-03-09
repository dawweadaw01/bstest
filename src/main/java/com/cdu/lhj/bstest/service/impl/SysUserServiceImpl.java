package com.cdu.lhj.bstest.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysUserMapper;
import com.cdu.lhj.bstest.pojo.SysUser;
import com.cdu.lhj.bstest.service.SysUserService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = "user", key = "#user.username")
    public boolean saveUser(SysUser user) {
        user.setPassword(SaSecureUtil.md5(user.getPassword()));
        user.setId(SimpleTimestampIdGenerator.nextId());
        return save(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "user", key = "#user.username")
    public boolean updateUser(SysUser user) {
        user.setPassword(SaSecureUtil.md5(user.getPassword()));
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
    @Cacheable(value = "user", key = "#username")
    public SysUser getUserByName(String username) {
        LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username);
        return getOne(eq);
    }

    @Override
    @Cacheable(value = "user", key = "#page + '-' + #size")
    public List<SysUser> listUsers(Integer page, Integer size) {
        // 分页查询
        Page<SysUser> sysUserPage = new Page<>(page, size);
        return page(sysUserPage).getRecords();
    }

}
