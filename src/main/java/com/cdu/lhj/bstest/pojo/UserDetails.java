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
@TableName(value = "user_details")
public class UserDetails {
    @TableId(value = "user_detail_id", type = IdType.INPUT)
    private Integer userDetailId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "gender")
    private String gender;

    @TableField(value = "birth_date")
    private Date birthDate;

    @TableField(value = "address")
    private String address;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "email")
    private String email;

    @TableField(value = "favorite_merchants")
    private String favoriteMerchants;

    @TableField(value = "favorite_cats")
    private String favoriteCats;
}