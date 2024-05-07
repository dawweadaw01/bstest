package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品图片表，存储商店中商品的图片信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shops_images")
public class ShopsImages {
    /**
     * 主键ID，唯一标识每一个图片
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商店ID，逻辑外键，关联商店表
     */
    @TableField(value = "store_id")
    private Long storeId;

    /**
     * 图片URL，存储图片的网络地址
     */
    @TableField(value = "image_url")
    private String imageUrl;

    /**
     * 图片描述，提供图片的简短描述
     */
    @TableField(value = "description")
    private String description;
}