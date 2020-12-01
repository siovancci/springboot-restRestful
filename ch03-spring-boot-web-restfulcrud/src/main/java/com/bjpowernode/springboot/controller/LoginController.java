package com.bjpowernode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/user/login")//相当于下面的
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,Map<String,Object> map,HttpSession session){
        if (!StringUtils.isEmpty(username)&&"vancci".equals(password)){

            //将用户放进session中,用来区分登录用户与未登录用户,防止用户直接通过地址访问我们的主页
            session.setAttribute("loginUser",username);

            //防止用户重复提交,使用重定向(注意:静态资源只能由转发完成,而我们防止用户重复提交必须用重定向,就需要这个视图转换器转换一下,由服务器来访问静态资源)
            return "redirect:/main";
        }else{
            map.put("msg","用户名密码错误");
            return "login";
        }

    }


}
