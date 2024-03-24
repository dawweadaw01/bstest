package com.cdu.lhj.bstest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdu.lhj.bstest.pojo.SysPermission;
import com.cdu.lhj.bstest.pojo.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    List<SysPermission> getPermissionByRoleId(Long roleId);

    List<SysPermission> getPermissionListForManager(Long roleId);
}