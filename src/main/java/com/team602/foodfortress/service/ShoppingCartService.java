package com.team602.foodfortress.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team602.foodfortress.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;


public interface ShoppingCartService extends IService<ShoppingCart> {
    public int addDish(ShoppingCart shoppingCart);


    public void delShoppingCartByUserId(String user_id);

    public Float calulateTotalAmountByUserID(String user_id);

    public List<ShoppingCart> getShoppingCartByUserId(String user_id);
}
