package com.team602.foodfortress.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team602.foodfortress.entity.User;
import com.team602.foodfortress.mapper.UserMapper;
import com.team602.foodfortress.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public int register(User user) {
        return baseMapper.insert(user);
    }
}
