package com.team602.foodfortress.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team602.foodfortress.entity.Business;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BusinessMapper extends BaseMapper<Business> {
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "id", property = "dishes",
//                    many = @Many(select = "com.team602.foodfortress.mapper.DishMapper.selectByBusinessId"))
//    })
    @Select("SELECT * FROM business WHERE id = #{id}")
    Business getBusinessById(@Param("id")String id);

    @Select("SELECT name FROM business WHERE id = #{id}")
    String getBusinessNameById(@Param("id")String id);
}
