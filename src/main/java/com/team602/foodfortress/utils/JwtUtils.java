package com.team602.foodfortress.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.team602.foodfortress.entity.User;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Date;

public class JwtUtils {
    //外部签名带的密钥
    public static final String SECRET = "ACC";

    //获取application.proprerties中配置的常量
    @Value("${secretkey}")
    private static final String SECRETKEY = null;
    //过期时间
    private static final Integer TIME_OUT = 3;
    //需要重新生成的 如果token的时间超过这个 则重新生成token
    private static final Integer NEED_CREATE = 2;

    private static final Integer  C= Calendar.MINUTE;

    //生成登录令牌的方法
    public static String sign(User user){
        //定义一个日历对象,配置令牌的有效期
        Calendar instance = Calendar.getInstance();
        // 120秒后令牌token失效
        //instance.add(Calendar.HOUR,2);
        instance.add(C,TIME_OUT);
        return JWT.create()
                //将什么内容放入到令牌中，自己决定 start
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                //.withClaim("userImg", user.getUserImg())
                .withClaim("secretkey",SECRETKEY)
                //将什么内容放入到令牌中，自己决定 end
                //设置令牌的有效期
                .withExpiresAt(instance.getTime())
                //设置加密模式
                .sign(Algorithm.HMAC256(SECRET));
    }



    //校验token
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT decodedJWT  = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //获取token内的携带的用户名信息
    public static String getUserIdByToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("id").asString();
    }

    /**
     * 判断token是否需要续签
     * @param token
     * @return
     */
    public static boolean needCreate(String token){

        DecodedJWT decodedJWT = JWT.decode(token);
        Date timeoutDate = decodedJWT.getExpiresAt();
        Calendar calendar = Calendar.getInstance();
        calendar.add(C,TIME_OUT - NEED_CREATE);
        if(timeoutDate.before(calendar.getTime())){
            return true;
        }
        return false;
    }
}
