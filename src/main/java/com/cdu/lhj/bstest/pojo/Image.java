package com.cdu.lhj.bstest.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "images")
public class Image {
    @TableId(value = "image_id", type = IdType.INPUT)
    private Long imageId;

    @TableField(value = "id")
    private Long id;

    @TableField(value = "image_url")
    private String imageUrl;

    @TableField(value = "description")
    private String description;
}