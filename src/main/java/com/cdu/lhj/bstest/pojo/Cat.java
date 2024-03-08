package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 具体猫咪信息表，存储每只猫咪的详细信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cat")
public class Cat {
    /**
     * 猫咪ID
     */
    @TableId(value = "cat_id", type = IdType.INPUT)
    private Long catId;

    /**
     * 猫咪种类ID，外键，引用cat_categories表的category_id
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 猫咪名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 猫咪年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 猫咪性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 猫咪喜好
     */
    @TableField(value = "preferences")
    private String preferences;

    /**
     * 猫咪健康状态
     */
    @TableField(value = "health_status")
    private String healthStatus;

    /**
     * 是否可领养
     */
    @TableField(value = "available_for_adoption")
    private Boolean availableForAdoption;

    /**
     * 商店ID
     */
    @TableField(value = "shop_id")
    private Long shopId;
    /**
     * 健康状态码
     */
    @TableField(value= "health_status_code")
    private Integer healthStatusCode;

    /**
     * 猫咪图片
     */
    @TableField(exist = false)
    List<Image> images;
}