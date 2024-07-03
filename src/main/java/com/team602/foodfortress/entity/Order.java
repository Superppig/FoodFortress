package com.team602.foodfortress.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Data
@TableName("orders")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String number;
    private Integer status;
    private String user_id;
    private String business_id;

    @TableField(fill = FieldFill.INSERT)
    private Date order_time;

    private Float amount;
    private String remark;

    @TableField(exist = false)
    private String phone;
    @TableField(exist = false)
    private String address;
    @TableField(exist = false)
    private String user_name;
    @TableField(exist = false)
    private String business_name;


    @TableField(exist = false)
    private Business business;
    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private List<OrderItem> orderItems;
}
