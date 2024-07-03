package com.team602.foodfortress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team602.foodfortress.entity.Business;
import com.team602.foodfortress.entity.Dish;
import com.team602.foodfortress.entity.User;
import com.team602.foodfortress.mapper.DishMapper;
import com.team602.foodfortress.mapper.DishMapperOne;
import com.team602.foodfortress.service.DishService;
import com.team602.foodfortress.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dishService")
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Override
    public int addDish(Dish dish) {
        return baseMapper.insert(dish);
    }

    @Override
    public Dish getDishById(Integer id) {
        return baseMapper.getDishById(id);
    }

    @Override
    public Page<Dish> getDishPageByBusinessId(Integer pageNum, Integer pageSize,String business_id) {
        Page<Dish> dishPage = baseMapper.getDishPageByBusinessId(new Page<Dish>(pageNum,pageSize),business_id);
        return dishPage;
    }

    @Override
    public List<Dish> searchDish(String keyword) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Dish::getName, keyword);
        return list(queryWrapper);
    }
}
