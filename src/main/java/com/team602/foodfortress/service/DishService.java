package com.team602.foodfortress.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team602.foodfortress.entity.Business;
import com.team602.foodfortress.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {

    public int addDish(Dish dish);
    public Dish getDishById(Integer id);

    public Page<Dish> getDishPageByBusinessId(Integer pageNum, Integer pageSize,String business_id);

    public List<Dish> searchDish(String keyword);
}