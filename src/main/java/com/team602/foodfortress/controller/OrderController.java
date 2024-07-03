package com.team602.foodfortress.controller;


import com.team602.foodfortress.entity.JsonResult;
import com.team602.foodfortress.entity.Order;
import com.team602.foodfortress.entity.User;
import com.team602.foodfortress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get/id/{id}")
    public JsonResult getOrderById(@PathVariable int id){
        Order order = orderService.getOrderByIdWithOrderItems(id);
        return new JsonResult(true,order,"获取订单成功");
    }

    @GetMapping("/get/number/{number}")
    public JsonResult getOrderByNumber(@PathVariable String number){
        Order order = orderService.getOrderByNumberWithOrderItems(number);
        return new JsonResult(true,order,"获取订单成功");
    }

    @GetMapping("/create")
    public JsonResult CreateOrder(@RequestParam(value = "user_id",required = true) String user_id,
                                  @RequestParam(value = "business_id",required = true) String business_id,
                                  @RequestParam(value = "remark",required = false,defaultValue = "还没有要求哦~")String remark)
    {
        int id = orderService.create(user_id,business_id,remark);
        return new JsonResult(true,id,"订单创建成功");
    }

    @GetMapping("/get/user_id/{user_id}")
    public JsonResult GetOrderPageByUserId(@PathVariable("user_id") String user_id,
                                           @RequestParam(value = "pn",required = false,defaultValue = "1") Integer pn,
                                           @RequestParam(value = "ps",required = false,defaultValue = "1") Integer ps){
        return new JsonResult(true,orderService.getOrderPageByUserId(user_id,pn,ps),"获取成功");
    }
}
