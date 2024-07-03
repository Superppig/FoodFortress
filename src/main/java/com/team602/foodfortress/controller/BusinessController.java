package com.team602.foodfortress.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team602.foodfortress.entity.Business;
import com.team602.foodfortress.entity.JsonResult;
import com.team602.foodfortress.entity.User;
import com.team602.foodfortress.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.ButtonUI;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @GetMapping("/page")
    private JsonResult BusinessPage(@RequestParam(value = "pn",defaultValue ="1" ,required = false) Integer pn,
                                    @RequestParam(value = "ps",defaultValue = "1",required = false) Integer ps){
        Page<Business> p=new Page<>(pn,ps);
        p=businessService.page(p);

        return new JsonResult(true, p,"获取商家页面成功");
    }

    @GetMapping("/select/{id}")
    private JsonResult GetBusiness(@PathVariable String id){
        Business business = businessService.getBusinessById(id);
        return new JsonResult(true , business,"获取单个商家成功");
    }

    @PostMapping("/update")
    private JsonResult UpdateBusiness(@RequestBody Business business){
        if(businessService.updateById(business)){
            return new JsonResult(true,"更新成功");
        }
        else {
            return new JsonResult(false,"更新失败");
        }
    }

    @PostMapping("/reg")
    private JsonResult RegesteBusiness(@RequestBody Business business){
        if(businessService.register(business)==1){
            return new JsonResult(true,businessService.getBusinessById(business.getId()),"注册商家成功");
        }
        else {
            return new JsonResult(false,"注册商家失败");
        }
    }

    @GetMapping("/del/{id}")
    private JsonResult DeleteBusiness(@PathVariable String id){
        if(businessService.removeById(id)){
            return new JsonResult(true,"删除商家成功");
        }
        else {
            return new JsonResult(false,"删除商家失败");
        }
    }
}
