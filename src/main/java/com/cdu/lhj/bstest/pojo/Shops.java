package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商店表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shops")
public class Shops {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 店铺名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 休息日
     */
    @TableField(value = "off_day")
    private String offDay;

    /**
     * 电话号码
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * Logo地址
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 封面地址
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 拥有人ID
     */
    @TableField(value = "owner_id")
    private Long ownerId;

    /**
     * 1为在运行，0为申请中，2为已下架
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 价格
     */
    @TableField(value = "fixed_price")
    private BigDecimal fixedPrice;
    /**
     * 猫咪图片地址
     */
    @TableField(exist = false)
    private List<String> CatImages;
    /**
     * 商店图片地址
     */
    @TableField(exist = false)
    private List<String> ShopImages;
    @TableField(exist = false)
    private List<ShopsImages> ShopImagesForManage;
}