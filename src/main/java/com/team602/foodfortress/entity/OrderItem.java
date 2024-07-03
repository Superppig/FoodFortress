package com.team602.foodfortress.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("order_item")
public class OrderItem {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer dish_id;
    private Integer order_id;
    private Integer number;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;

    @TableField(exist = false)
    private Dish dish;
}
