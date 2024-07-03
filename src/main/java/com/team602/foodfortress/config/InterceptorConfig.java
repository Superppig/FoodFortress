package com.team602.foodfortress.config;

import com.team602.foodfortress.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //后端允许跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许跨域的规则
        registry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //注册TestInterceptor拦截器
//        registry.addInterceptor(createLoginInterceptor())
//                //排除在此拦截器外
//                .excludePathPatterns("/user/login")
//                //被拦截的规则
//                .addPathPatterns("/**");
//    }

    @Bean
    public LoginInterceptor createLoginInterceptor(){
        return new LoginInterceptor();
    }
}
