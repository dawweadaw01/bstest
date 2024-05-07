package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约表，存储用户的预约信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "appointments")
public class Appointments {
    /**
     * 主键ID，唯一标识每个预约
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商店ID，标识预约所属的商店
     */
    @TableField(value = "store_id")
    private Long storeId;

    /**
     * 预约时间，记录预约的具体时间
     */
    @TableField(value = "appointment_time")
    private Date appointmentTime;

    /**
     * 用户ID，标识预约的用户
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 下单状态，描述预约的当前状态
     */
    @TableField(value = "order_status")
    private Long orderStatus;

    /**
     * 价格
     */
    @TableField(value = "fixed_price")
    private BigDecimal fixedPrice;
    @TableField(exist = false)
    private String appointmentTimeStr;
}