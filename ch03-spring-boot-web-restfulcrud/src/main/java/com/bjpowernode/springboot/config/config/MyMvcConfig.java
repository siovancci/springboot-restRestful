package com.bjpowernode.springboot.config.config;

import com.bjpowernode.springboot.component.LoginHandlerInterceptor;
import com.bjpowernode.springboot.component.MyLocaleResolver;
import com.bjpowernode.springboot.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        //由于重定向不能直接访问静态资源,所以用main来接收一下再转到dashboard.html
        registry.addViewController("/main").setViewName("dashboard");
    }

    //配置自定义的区域信息解析器
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


    /*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] execludeList = new String[]{"/","/login.html","/user/login","/asserts/**","/webjars/**"};

        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(execludeList);
    }*/


}
