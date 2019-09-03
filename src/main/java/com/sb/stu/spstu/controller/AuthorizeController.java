package com.sb.stu.spstu.controller;

import com.sb.stu.spstu.domain.User;
import com.sb.stu.spstu.dto.AccessTokenDto;
import com.sb.stu.spstu.dto.GithubUser;
import com.sb.stu.spstu.mapper.UserMapper;
import com.sb.stu.spstu.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.Client.id}")
    private String ClientId;
    @Value("${github.Client.secret}")
    private String ClientSecret;
    @Value("${github.Redirect.uri}")
    private String RedirectUri;
    @Resource
    private UserMapper userMapper;
    @RequestMapping("callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state")  String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        AccessTokenDto accessTokenDto=new AccessTokenDto();
        accessTokenDto.setClient_id(ClientId);
        accessTokenDto.setClient_secret(ClientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(RedirectUri);
        accessTokenDto.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDto);
       GithubUser user=githubProvider.getUser(accessToken);
       if(user!=null){
           User use=new User();
          String token= UUID.randomUUID().toString();
           use.setToken(token);
           use.setAccountId(String.valueOf(user.getId()));
           use.setName(user.getName());
           use.setGmtCreate(System.currentTimeMillis());
           use.setModified(use.getGmtCreate());
           userMapper.addUser(use);
           response.addCookie(new Cookie("token",token));
           return "redirect:/";
       }else {

           return "index";
       }
    }

}
