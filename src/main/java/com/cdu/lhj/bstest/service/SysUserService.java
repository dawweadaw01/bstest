package com.cdu.lhj.bstest.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cdu.lhj.bstest.pojo.Bo.LoginSmsBo;
import com.cdu.lhj.bstest.pojo.Bo.UpdatePhoneBo;
import com.cdu.lhj.bstest.pojo.Bo.UpdatePwdBo;
import com.cdu.lhj.bstest.pojo.Bo.UserSearchBo;
import com.cdu.lhj.bstest.pojo.SysUser;

public interface SysUserService extends IService<SysUser> {

    SysUser doLogin(String username, String password);

    boolean saveUser(SysUser user);

    boolean updateUser(SysUser user);

    boolean deleteUser(Long userId);

    SysUser getUserById(Long userId);

    SysUser getUserByName(String username);

    IPage<SysUser> listUsers(UserSearchBo userSearchBo);

    SysUser doLoginByPhoneCode(LoginSmsBo loginSmsBo);

    SysUser getUserByPhone(String phone);

    boolean updateUserPhone(UpdatePhoneBo updatePhoneBo);

    boolean updateUserPwd(UpdatePwdBo updatePwdBo);
}
