package com.team602.foodfortress.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team602.foodfortress.entity.Dish;
import com.team602.foodfortress.entity.JsonResult;
import com.team602.foodfortress.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/page/{business_id}")
    public JsonResult getDishPageByBusinessId(@RequestParam(value = "pn",defaultValue ="1" ,required = false) Integer pn,
                                              @RequestParam(value = "ps",defaultValue = "1",required = false) Integer ps,
                                              @PathVariable String business_id){
        Page<Dish> page =  dishService.getDishPageByBusinessId(pn,ps, business_id);
        return new JsonResult(true,page,"查询商品页面成功");
    }
}
