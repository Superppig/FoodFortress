package com.team602.foodfortress.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team602.foodfortress.entity.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    public int create(String user_id,String business_id,String remark);
    public Order getOrderById(int id);
    public Order getOrderByNumber(String number);

    public Order getOrderByIdWithOrderItems(int id);
    public Order getOrderByNumberWithOrderItems(String number);

    public Page<Order> getOrderPageByUserId(String user_id , Integer page_num,Integer page_size);

}
