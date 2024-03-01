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
@TableName(value = "sys_user_role")
public class SysUserRole {
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;
}