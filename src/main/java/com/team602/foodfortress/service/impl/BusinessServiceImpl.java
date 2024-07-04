package com.team602.foodfortress.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team602.foodfortress.entity.Business;
import com.team602.foodfortress.entity.Dish;
import com.team602.foodfortress.mapper.BusinessMapper;
import com.team602.foodfortress.mapper.DishMapper;
import com.team602.foodfortress.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("businessService")
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private DishMapper dishMapper;

    @Override
    public Business getBusinessById(String id) {
        return baseMapper.getBusinessById(id);
    }

    @Override
    public int register(Business business) {
        String name = business.getName();
        Long cont = baseMapper.selectCount(new LambdaQueryWrapper<Business>().eq(Business::getName, name));
        if (cont == 0) {
            return baseMapper.insert(business);
        }
        return 0;
    }

    @Override
    public List<Business> searchBusiness(String keyword) {
        LambdaQueryWrapper<Business> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Business::getName, keyword);
        return list(queryWrapper);
    }

    @Override
    public List<Dish> getDishesByBusinessId(String business_id) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey("business" + business_id))) {
            List<Dish> dishes = new ArrayList<>();
            List<Object> range = redisTemplate.opsForList().range("business" + business_id, 0, -1);
            System.out.println("log: " + range);
            if(range == null) return new ArrayList<>();
            for (Object o : range) {
                System.out.println(o.getClass());
                o=(JSONArray)o;
                o=((JSONArray) o).get(0);
                dishes.add(JSON.parseObject(o.toString(), Dish.class));
            }

            return dishes;
        }
        List<Dish> dishes = dishMapper.selectList(new LambdaQueryWrapper<Dish>().eq(Dish::getBusiness_id, business_id));
        redisTemplate.opsForList().rightPushAll("business" + business_id, dishes);
        redisTemplate.expire("business" + business_id, 60, TimeUnit.MINUTES);
        return dishes;
    }
}
