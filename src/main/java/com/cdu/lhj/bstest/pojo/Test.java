package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("test")
public class Test {
    /**
     * 使用mybatis-plus的注解定义字段对应的表字段
     */
    @TableField("test")
    private String test;
}