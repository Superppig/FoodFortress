package com.team602.foodfortress.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team602.foodfortress.entity.JsonResult;
import com.team602.foodfortress.entity.User;
import com.team602.foodfortress.service.UserService;
import com.team602.foodfortress.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    //控制器  调 业务逻辑层
    //业务逻辑层 调 数据持久层
    @Autowired
    private UserService userService;

    /**
     *
     * @param user
     * @return String RestController注解下 response.body 响应正文
     * Controller 注解下 String  视图名 网页模板的名称
     */
    @PostMapping("/reg")
    public JsonResult reg(@RequestBody User user)
    {
        //1、取  取参数封装  看方法的参数
        // 2、调 业务逻辑层
        int rs= userService.register(user);
        // 3、转  输出结果
        //return
        if(rs==1){
            return new JsonResult(true,"注册成功");
        }else{
            return new JsonResult(false,"注册失败");
        }
    }

    /**
     * http://localhost:8001/user/login
     * @param user
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody User user){
        //1
        //2
        System.out.println(user);
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName,user.getName())
                .eq(User::getPassword,user.getPassword());
        User u= userService.getOne(lambdaQueryWrapper);
        if(u!=null){
            //3、
            //生成令牌token字符串
            String token= JwtUtils.sign(u);
            return new JsonResult(true,u,token);
        }else {
            return new JsonResult(false,"用户名或密码错误！");
        }
    }
    /**
     * http://localhost:8001/user/login
     * @param user
     * @return
     */
    @PostMapping("/loginInfo")
    public JsonResult login(HttpServletRequest request){
        //取token字符串
        String token=request.getHeader("Authorization");
        System.out.println(token);

        if (token == null || token.trim().isEmpty()) {
            return new JsonResult(false, "token为空");
        }
        if(token.startsWith("Bearer "))
        {
            token = token.substring(7);
            System.out.println(token);
        }
        else{
            return new JsonResult(false, "token格式错误");
        }
        String userId;
        try {
            userId = JwtUtils.getUserIdByToken(token);
        } catch (Exception e) {
            return new JsonResult(false, "解析token时出错: " + token);
        };

        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,userId);
        User u= userService.getOne(lambdaQueryWrapper);

        if(u!=null){
            if(JwtUtils.needCreate(token)){
                //续期
                System.out.println("续期");
                token= JwtUtils.sign(u);
                return new JsonResult(true,u,token);
            }
            return new JsonResult(u);
        }else {
            return new JsonResult(false,"token令牌有误");
        }
    }
    /**
     * url:
     * http://localhost:8001/user/page
     * @param pn
     * @param ps
     * @return
     */
    @GetMapping("/page")
    public JsonResult getUsers(@RequestParam(value = "pn",defaultValue ="1" ,required = false) Integer pn,
                               @RequestParam(value = "ps",defaultValue = "1",required = false) Integer ps){
        //1
        //2
//        selectPage
        Page<User> p=new Page<>(pn,ps);
        p=userService.page(p);
        //3
        return new JsonResult(p);
    }


    @PostMapping("/update")
    public JsonResult updateUser(@RequestBody User user)
    {
        if(userService.updateById(user)){
            return new JsonResult(true,user,"更新成功");
        }
        else {
            return new JsonResult(false,"更新失败");
        }
    }
}
