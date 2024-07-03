package com.team602.foodfortress.interceptor;

import com.team602.foodfortress.entity.JsonResult;
import com.team602.foodfortress.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        System.out.println(token);
        if(token.startsWith("Bearer "))
        {
            token = token.substring(7);
        }
        else{
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(new JsonResult(false,"1001","token格式错误"));
        }

        if(token!=null&&!token.equals("")){
            if(JwtUtils.verify(token)){
                return true;
            }else {
                response.setCharacterEncoding("utf-8");
                response.getWriter().println(new JsonResult(false,"1001","token无效"));
            }
        }else{
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(new JsonResult(false,"1001","未登录"));

        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
