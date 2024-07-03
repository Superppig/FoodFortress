package com.team602.foodfortress.controller;


import com.team602.foodfortress.entity.Business;
import com.team602.foodfortress.entity.JsonResult;
import com.team602.foodfortress.service.BusinessService;
import com.team602.foodfortress.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private BusinessService businessService;
    @Autowired
    private DishService dishService;

    @GetMapping("/business")
    private JsonResult SearchBusiness(@RequestParam(value = "keyword") String keyword){
        return new JsonResult(true,businessService.searchBusiness(keyword),"搜索成功");
    }

    @GetMapping("/dish")
    private JsonResult SearchDish(@RequestParam(value = "keyword") String keyword){
        return new JsonResult(true,dishService.searchDish(keyword),"搜索成功");
    }
}
