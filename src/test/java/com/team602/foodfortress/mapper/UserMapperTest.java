package com.team602.foodfortress.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team602.foodfortress.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public  void addTest(){
        //1、封装对象
        User user=new User();
        user.setId("12345672221");
        user.setName("张三");
        user.setSex(0);
        user.setImage("+0zWPUl8tYMA1qOwi82bxzAAv");
        user.setPassword("1234567");
        user.setPhone("13458888888");
        //2、调用方法
        int rs= userMapper.insert(user);
        //3、判断结果
        if(rs==1){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }
    @Test
    public  void update(){
        //1、封装对象
        User user=new User();
        user.setId("12345672221");
        user.setName("jack");
        user.setSex(0);
        user.setImage("+0zWPUl8tYMA1qOwi8");
        user.setPassword("123");
        user.setPhone("13458888888");
        //2、调用方法
        int rs= userMapper.updateById(user);
        //3、判断结果
        if(rs==1){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }
    @Test
    public void del(){
        //1、封装对象
        User user=new User();
        user.setId("12345672221");
        //2、调用方法
        int rs= userMapper.deleteById(user);
        //3、判断结果
        if(rs==1){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    public void getUserById(){

        //查询条件
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper();
        //查询条件
        lambdaQueryWrapper.eq(User::getId,"111");
        //查询列
        lambdaQueryWrapper.select(true,User::getId,User::getName);

        User user= userMapper.selectOne(lambdaQueryWrapper);
        System.out.println(user);
    }

    @Test
    public void getUserById2(){
        //查询条件
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //查询条件
        queryWrapper.eq("userId","1213131231");
        //查询列
        queryWrapper.select(true,"userId","userName","userSex");

        User user= userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }
}
