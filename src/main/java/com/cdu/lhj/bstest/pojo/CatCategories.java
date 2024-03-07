package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;import java.util.List;

/**
 * 猫咪种类信息表，存储各种猫咪的种类信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cat_categories")
public class CatCategories {
    @TableId(value = "category_id", type = IdType.INPUT)
    private Long categoryId;

    /**
     * 猫咪种类名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 猫咪种类的原产地
     */
    @TableField(value = "origin")
    private String origin;

    /**
     * 种类描述
     */
    @TableField(value = "description")
    private String description;
    // 不和数据库对应，自己组装
    @TableField(exist = false)
    private List<Image> images;
}