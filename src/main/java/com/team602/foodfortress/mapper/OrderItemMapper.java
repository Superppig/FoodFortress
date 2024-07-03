package com.team602.foodfortress.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team602.foodfortress.entity.Dish;
import com.team602.foodfortress.entity.Order;
import com.team602.foodfortress.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
