package com.team602.foodfortress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team602.foodfortress.entity.Business;
import com.team602.foodfortress.mapper.BusinessMapper;
import com.team602.foodfortress.service.BusinessService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("businessService")
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Override
    public Business getBusinessById(String id) {
        return baseMapper.getBusinessById(id);
    }

    @Override
    public int register(Business business) {
        return baseMapper.insert(business);
    }

    @Override
    public List<Business> searchBusiness(String keyword) {
        LambdaQueryWrapper<Business> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Business::getName, keyword);
        return list(queryWrapper);
    }
}
