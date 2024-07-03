package com.team602.foodfortress.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team602.foodfortress.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
