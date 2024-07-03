package com.team602.foodfortress.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team602.foodfortress.entity.Dish;
import com.team602.foodfortress.entity.ShoppingCart;
import com.team602.foodfortress.mapper.ShoppingCartMapper;
import com.team602.foodfortress.service.DishService;
import com.team602.foodfortress.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>implements ShoppingCartService {

    @Autowired
    private DishService dishService;
    @Override
    @Transactional
    public int addDish(ShoppingCart shoppingCart) {
        Dish dish = dishService.getDishById(shoppingCart.getDish_id());
        shoppingCart.setAmount(dish.getPrice());
        shoppingCart.setName(dish.getName());
        shoppingCart.setImage(dish.getImage());

        //检查购物车中是否已经存在相应记录
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUser_id,shoppingCart.getUser_id())
                .eq(ShoppingCart::getDish_id,shoppingCart.getDish_id());
        ShoppingCart existingCart = baseMapper.selectOne(queryWrapper);

        if(existingCart!=null){
            shoppingCart.setId(existingCart.getId());
            if(shoppingCart.getNumber()==0){
                return baseMapper.deleteById(shoppingCart);
            }
            else{
                // 更新 ShoppingCart 记录
                return baseMapper.updateById(shoppingCart);
            }
        }
        else {
            return baseMapper.insert(shoppingCart);
        }
    }

    @Override
    public void delShoppingCartByUserId(String user_id) {
        List<ShoppingCart> shoppingCarts = getShoppingCartByUserId(user_id);
        for (ShoppingCart shoppingCart:shoppingCarts){
            baseMapper.deleteById(shoppingCart);
        }
    }

    @Override
    public Float calulateTotalAmountByUserID(String user_id) {
        Float amount = 0f;

        List<ShoppingCart> shoppingCarts = getShoppingCartByUserId(user_id);
        for (ShoppingCart shoppingCart:shoppingCarts){
            amount += shoppingCart.getAmount()*shoppingCart.getNumber();
        }
        return amount;
    }

    @Override
    public List<ShoppingCart> getShoppingCartByUserId(String user_id) {
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUser_id,user_id);
        List<ShoppingCart> shoppingCarts = list(lambdaQueryWrapper);
        return shoppingCarts;
    }
}
