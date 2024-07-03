package com.team602.foodfortress.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("dishes")
public class Dish {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Float price;
    private String image;
    private String description;
    private Integer status;

    private String business_id;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    @TableField(exist = false)
    private String businessName;
    @TableField(exist = false)
    private Business business;


}
