package com.example.fengkou_2.InterCeptor.Config;

import com.example.fengkou_2.InterCeptor.InterCeptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/tiku/**","/zhishidian/**",
                        "/zhishiku/**","/login","/register","/forgetpassword1",
                        "/assets/**","/css/**","/fonts/**","/forms/**","/forgetpassword",
                        "/images/**","/js/**","/languages/**","/lib/**","/plugins/**");

    }
}
