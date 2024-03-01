package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role_permission")
public class SysRolePermission {
    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;

    @TableId(value = "permission_id", type = IdType.INPUT)
    private Long permissionId;
}