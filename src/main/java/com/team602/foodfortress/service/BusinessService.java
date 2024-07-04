package com.team602.foodfortress.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team602.foodfortress.entity.Business;
import com.team602.foodfortress.entity.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessService extends IService<Business> {
    public Business getBusinessById(String id);
    public int register(Business business);

    public List<Business> searchBusiness(String keyword);

    List<Dish> getDishesByBusinessId(String business_id);
}
