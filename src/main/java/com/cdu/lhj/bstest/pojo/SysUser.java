package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUser {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "`password`")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "gender")
    private Integer gender;

    @TableField(value = "birth_date")
    private Date birthDate;

    @TableField(value = "address")
    private String address;

    @TableField(value = "autograph")
    private String autograph;
    @TableField(exist = false)
    private String roles;
    @TableField(exist = false)
    private String avatar;
}