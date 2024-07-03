package com.team602.foodfortress.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team602.foodfortress.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select orders.*,business.name as business_name,user.name as user_name,user.phone as user_phone,user.address as user_address "+
            " from orders,business,user "+
    " where orders.business_id = business.id and orders.user_id = user.id and orders.id = #{id}")
    @Results({
            @Result(column = "business_id",property = "business_id"),
            @Result(column = "business_name",property = "business_name"),


            @Result(column = "user_id",property = "user_id"),
            @Result(column = "user_name",property = "user_name"),
            @Result(column = "user_phone",property = "phone"),
            @Result(column = "user_address",property = "address"),
    })
    Order getOrderById(@Param("id") int id);



    @Select("select orders.*,business.name as business_name,user.name as user_name,user.phone as user_phone,user.address as user_address "+
            " from orders,business,user "+
            " where orders.business_id = business.id and orders.user_id = user.id and orders.number = #{number}")
    @Results({
            @Result(column = "business_id",property = "business_id"),
            @Result(column = "business_name",property = "business_name"),


            @Result(column = "user_id",property = "user_id"),
            @Result(column = "user_name",property = "user_name"),
            @Result(column = "user_phone",property = "phone"),
            @Result(column = "user_address",property = "address")
    })
    Order getOrderByNumber(@Param("number") String number);


    @Select("select orders.*,business.name as business_name,user.name as user_name,user.phone as user_phone,user.address as user_address "+
            " from orders,business,user "+
            " where orders.business_id = business.id and orders.user_id = user.id and orders.user_id = #{user_id}")
    @Results({
            @Result(column = "business_id",property = "business_id"),
            @Result(column = "business_name",property = "business_name"),


            @Result(column = "user_id",property = "user_id"),
            @Result(column = "user_name",property = "user_name"),
            @Result(column = "user_phone",property = "phone"),
            @Result(column = "user_address",property = "address")
    })
    Page<Order> getOrderPageByUserId(Page<Order> pg,@Param("user_id") String user_id);
}
