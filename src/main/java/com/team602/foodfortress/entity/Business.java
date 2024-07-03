package com.team602.foodfortress.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("business")
public class Business {
   @TableId(value = "id")
   private String id;
   private String name;
   private String image;

   @TableField(fill = FieldFill.INSERT)
   private Date create_time;
   @TableField(fill = FieldFill.INSERT_UPDATE)
   private Date update_time;


   @TableField(exist = false)
   private List<Dish> dishes;
}
