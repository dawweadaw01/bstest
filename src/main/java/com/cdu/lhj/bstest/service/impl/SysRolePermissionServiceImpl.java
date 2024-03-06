package com.cdu.lhj.bstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdu.lhj.bstest.mapper.SysRolePermissionMapper;
import com.cdu.lhj.bstest.pojo.SysPermission;
import com.cdu.lhj.bstest.pojo.SysRolePermission;
import com.cdu.lhj.bstest.service.SysRolePermissionService;
import com.cdu.lhj.bstest.util.SimpleTimestampIdGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {


    @Override
    public List<SysPermission> getPermissionByRoleId(Long roleId) {
        //调用mapper查询
        return getBaseMapper().getPermissionByRoleId(roleId);
    }

    @Override
    public boolean saveRolePermission(SysRolePermission rolePermission) {
        rolePermission.setId(SimpleTimestampIdGenerator.nextId());
        return save(rolePermission);
    }
}
