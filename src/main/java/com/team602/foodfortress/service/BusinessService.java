package com.team602.foodfortress.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team602.foodfortress.entity.Business;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessService extends IService<Business> {
    public Business getBusinessById(String id);
<<<<<<< HEAD
    public int register(Business business);

    public List<Business> searchBusiness(String keyword);
=======
    public int regester(Business business);
>>>>>>> parent of 326d16dd (修改部分拼写错误，完善注册登录功能)
}
