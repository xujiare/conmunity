package com.sb.stu.spstu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebParam;
@Controller
public class HelloController {
    @RequestMapping("hello")
    public  String hello(@RequestParam(name="username")String name, Model model){
                model.addAttribute("name",name);
                return "hello";

    }
    @RequestMapping("/")
    public  String hello(){
        return "index";

    }
}
