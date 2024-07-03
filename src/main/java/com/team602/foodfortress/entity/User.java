package com.team602.foodfortress.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {
    @TableId(value = "id")
    private String id;

    @TableField(select = false)
    private String password;

    private String name;
    private Integer sex;
    private String image;
    private String phone;
    private String address;

    @TableField(fill = FieldFill.INSERT)
    private Date create_time;
}