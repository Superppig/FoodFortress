package com.team602.foodfortress.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.team602.foodfortress.entity.JsonResult;
import com.team602.foodfortress.entity.ShoppingCart;
import com.team602.foodfortress.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;


    @PostMapping("/add")
    private JsonResult AddShoppingCart(@RequestBody ShoppingCart shoppingCart){
        if(shoppingCartService.addDish(shoppingCart)==1){
            return new JsonResult(true,shoppingCartService.getById(shoppingCart.getId()),"添加购物车成功");
        }
        else return new JsonResult(false,"插入失败");
    }
    @GetMapping("/show/{user_id}")
    private JsonResult ShowShoppingCart(@PathVariable String user_id){

        return new JsonResult(true,shoppingCartService.getShoppingCartByUserId(user_id),"获取成功");
    }

    @GetMapping("/amount/{user_id}")
    private JsonResult GetShoppingCartAmount(@PathVariable String user_id){
        return new JsonResult(true,shoppingCartService.calulateTotalAmountByUserID(user_id),"获取成功");
    }
}