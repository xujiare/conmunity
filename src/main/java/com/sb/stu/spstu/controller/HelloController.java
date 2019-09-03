package com.sb.stu.spstu.controller;

import com.sb.stu.spstu.domain.User;
import com.sb.stu.spstu.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    @RequestMapping("ts")
    public  String hello1(){
        return "test";

    }
    @Resource
   private  UserMapper userMapper;
    @RequestMapping("/")
    //检验是否登陆过
    public  String hello(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie c:cookies){
            if("token".equals(c.getName())){
                String token=c.getValue();
                User user= userMapper.findBytoken(token);
                request.getSession().setAttribute("user",user);
            }

        }
        return "index";

    }
}
