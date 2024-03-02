package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.SysUser;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    SysUser doLogin(String username, String password);

    boolean saveUser(SysUser user);

    boolean updateUser(SysUser user);

    boolean deleteUser(Long userId);

    SysUser getUserById(Long userId);

    SysUser getUserByName(String username);

    List<SysUser> listUsers(Integer page, Integer size);
}
