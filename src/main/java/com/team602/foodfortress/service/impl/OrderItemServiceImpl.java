package com.team602.foodfortress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team602.foodfortress.entity.OrderItem;
import com.team602.foodfortress.mapper.OrderItemMapper;
import com.team602.foodfortress.service.DishService;
import com.team602.foodfortress.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    @Autowired
    private DishService dishService;
    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer order_id) {
        List<OrderItem> orderItems= lambdaQuery().eq(OrderItem::getOrder_id,order_id).list();
        for (OrderItem o:orderItems) {
            o.setDish(dishService.getDishById(o.getDish_id()));
        }
        return orderItems;
    }
}