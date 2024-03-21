package com.cdu.lhj.bstest.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysUserMapper;
import com.cdu.lhj.bstest.pojo.Bo.LoginSmsBo;
import com.cdu.lhj.bstest.pojo.Bo.UserSearchBo;
import com.cdu.lhj.bstest.pojo.SysUser;
import com.cdu.lhj.bstest.service.SysUserService;
import com.cdu.lhj.bstest.util.RedisUtil;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private RedisUtil redisUtil;
    @Override
    public SysUser doLogin(String username, String password) {
        LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username).eq(SysUser::getPassword, password);
        return getOne(eq);
    }

    @Override
    @Transactional
    @CacheEvict(value = "user", allEntries = true)
    public boolean saveUser(SysUser user) {
        user.setPassword(SaSecureUtil.md5(user.getPassword()));
        user.setId(SimpleTimestampIdGenerator.nextId());
        return save(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "user", allEntries = true)
    public boolean updateUser(SysUser user) {
        if(user.getPassword() != null){
            user.setPassword(SaSecureUtil.md5(user.getPassword()));
        }
        return this.baseMapper.updateUser(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "user", allEntries = true)
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
    public SysUser getUserByPhone(String phone) {
        LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>().eq(SysUser::getPhone, phone);

        return getOne(eq);
    }

    @Override
    @Cacheable(value = "user", key = "#userSearchBo.username + '-' + #userSearchBo.phone + '-' + #userSearchBo.page + '-' + #userSearchBo.size")
    public IPage<SysUser> listUsers(UserSearchBo userSearchBo) {
        // 分页查询
        Page<SysUser> sysUserPage = new Page<>(userSearchBo.getPage(), userSearchBo.getSize());
        // 查询条件
        return this.baseMapper.listUsers(sysUserPage, userSearchBo);
    }

    @Override
    public SysUser doLoginByPhoneCode(LoginSmsBo loginSmsBo) {
        // 根据手机号查询用户
        LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>().eq(SysUser::getPhone, loginSmsBo.getPhoneNum());
        SysUser sysUser = getOne(eq);
        if(sysUser == null){
            throw new RuntimeException("手机号未注册");
        }
        boolean hasKey = redisUtil.hasKey("code:" + loginSmsBo.getPhoneNum());
        if (!hasKey) {
            throw new RuntimeException("验证码已过期");
        }else {
            String code = (String) redisUtil.get("code:" + loginSmsBo.getPhoneNum());
            if (!code.equals(loginSmsBo.getCode())) {
                throw new RuntimeException("验证码错误");
            }
        }
        return sysUser;
    }

}
