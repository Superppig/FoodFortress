package com.team602.foodfortress.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team602.foodfortress.entity.*;
import com.team602.foodfortress.mapper.OrderItemMapper;
import com.team602.foodfortress.mapper.OrderMapper;
import com.team602.foodfortress.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private DishService dishService;
    @Autowired
    private UserService userService;


    private static final String OrderKey = "TEAM602";

    @Override
    @Transactional
    public int create(String user_id,String business_id,String remark) {
        Order order = new Order();
        order.setStatus(0);

        order.setUser_id(user_id);
        order.setBusiness_id(business_id);
        order.setRemark(remark);
        order.setAddress(userService.getById(user_id).getAddress());
        //订单号生成逻辑
        order.setNumber(OrderKey+  new Date().toString()+user_id+business_id);

        //TODO:编写自动计算价格等
        order.setAmount(shoppingCartService.calulateTotalAmountByUserID(user_id));

        //插入order
        baseMapper.insert(order);

        //根据购物车添加OrderItem
        List<ShoppingCart> shoppingCarts=shoppingCartService.getShoppingCartByUserId(user_id);
        for (ShoppingCart shoppingCart:shoppingCarts){
            OrderItem orderItem = new OrderItem();
            orderItem.setDish_id(shoppingCart.getDish_id());
            orderItem.setNumber(shoppingCart.getNumber());
            orderItem.setOrder_id(order.getId());
            orderItemService.getBaseMapper().insert(orderItem);
        }
        //删除购物车记录
        shoppingCartService.delShoppingCartByUserId(user_id);
        return order.getId();
    }

    @Override
    public Order getOrderById(int id) {
        return baseMapper.getOrderById(id);
    }

    @Override
    public Order getOrderByNumber(String number) {
        return baseMapper.getOrderByNumber(number);
    }

    @Override
    public Order getOrderByIdWithOrderItems(int id) {
        Order order = baseMapper.getOrderById(id);
        order.setOrderItems(orderItemService.getOrderItemsByOrderId(order.getId()));
        return order;
    }

    @Override
    public Order getOrderByNumberWithOrderItems(String number) {
        Order order = baseMapper.getOrderByNumber(number);
        order.setOrderItems(orderItemService.getOrderItemsByOrderId(order.getId()));
        return order;
    }

    @Override
    public Page<Order> getOrderPageByUserId(String user_id , Integer page_num,Integer page_size) {
        return baseMapper.getOrderPageByUserId(new Page<Order>(page_num,page_size),user_id);
    }
}
