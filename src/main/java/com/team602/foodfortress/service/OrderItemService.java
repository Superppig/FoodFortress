package com.team602.foodfortress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team602.foodfortress.entity.Order;
import com.team602.foodfortress.entity.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderItemService extends IService<OrderItem> {
    public List<OrderItem> getOrderItemsByOrderId(Integer order_id);
}
