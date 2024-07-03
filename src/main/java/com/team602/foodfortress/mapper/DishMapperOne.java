package com.team602.foodfortress.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team602.foodfortress.entity.Dish;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DishMapperOne extends BaseMapper<Dish> {
    //@Results 注解来映射查询结果集到实体类属性
    /*
    * 当我们需要通过查询到的一个字段值作为参数，去执行另外一个方法来查询关联的内容，而且两者是一对一关系时，可以使用 @One 注解来便捷的实现。
      selectById 方法是 BaseMapper 就提供的，所以我们不需要在 AreaMapper 中手动定义。
      @Result(column = "area_id", property = "areaId") 可以不写，也不会报错。但是会导致我们查询结果（User 实体）的 areaId 属性没有值（因为第二个 Result 将 area_id 值作为查询条件传入子查询）。
    */
    @Results({
            @Result(column = "business_id", property = "business",
                    one = @One(select = "com.team602.foodfortress.mapper.BusinessMapper.getBusinessById")),
    })
    @Select("SELECT * FROM dishes WHERE id = #{id}")
    Dish getDishById(int id);
}
