package com.team602.foodfortress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team602.foodfortress.entity.User;

public interface UserService extends IService<User> {
    public int register(User user);
}
