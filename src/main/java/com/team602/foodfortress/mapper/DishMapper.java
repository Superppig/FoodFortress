package com.team602.foodfortress.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team602.foodfortress.entity.Dish;
import org.apache.ibatis.annotations.*;


@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    @Results({
            @Result(column = "business_id", property = "business_id"),
            @Result(column = "business_name", property = "businessName")
    })
    @Select("select dishes.*,business.name as business_name from dishes,business "+
    " where dishes.business_id = business.id and dishes.id = #{id}"
    )
    Dish getDishById(@Param("id") int id);
    @Select("SELECT * FROM dishes WHERE business_id = #{business_id}")
    Dish selectByBusinessId(@Param("business_id") String business_id);


    Dish selectByOrderId(Integer order_id);

    @Results({
            @Result(column = "business_id", property = "business_id"),
            @Result(column = "business_name", property = "businessName")
    })
    @Select("select dishes.*,business.name as business_name from dishes,business "+
            " where dishes.business_id = business.id and dishes.business_id = #{business_id}"
    )
     Page<Dish> getDishPageByBusinessId(Page<Dish> pg, @Param("business_id") String business_id);
}
