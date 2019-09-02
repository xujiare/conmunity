package com.sb.stu.spstu.controller;

import com.sb.stu.spstu.dto.AccessTokenDto;
import com.sb.stu.spstu.dto.GithubUser;
import com.sb.stu.spstu.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @RequestMapping("callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state")  String state){

        AccessTokenDto accessTokenDto=new AccessTokenDto();
        accessTokenDto.setClient_id("006f837773eb18dddf31");
        accessTokenDto.setClient_secret("942586e92758eddf6dda26673284bb7fd8b005c6");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8888/callback");
        accessTokenDto.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDto);
       GithubUser user=githubProvider.getUser(accessToken);
       System.out.println(user.getName());
        return "index";

    }

}
