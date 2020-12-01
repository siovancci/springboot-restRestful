package com.bjpowernode.springboot.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//在链接上携带区域信息
public class MyLocaleResolver implements LocaleResolver{

    //解析区域信息的值
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l = httpServletRequest.getParameter("l");
        //如果参数上没有l就用默认的区域信息解析器,解析请求头的
        Locale locale = Locale.getDefault();
        //如果前端你传来的l不为null,用我们下面自定义的区域信息解析器,将参数分开,用一个两个参数的构造方法来构造区域信息
        if (!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }


}
