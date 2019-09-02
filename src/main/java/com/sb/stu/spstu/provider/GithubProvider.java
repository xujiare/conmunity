package com.sb.stu.spstu.provider;

import com.alibaba.fastjson.JSON;
import com.sb.stu.spstu.dto.AccessTokenDto;
import com.sb.stu.spstu.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public  String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                 String str=response.body().string();
                String token= str.split("&")[0].split("=")[1];
                 System.out.println(token);
                return token;
            }catch (IOException e){

            }
            return null;

            }

        public GithubUser getUser(String accessToken){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(" https://api.github.com/user?access_token="+accessToken)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String str=response.body().string();
                //讲json转化为对象
                GithubUser githubUser=JSON.parseObject(str,GithubUser.class);
                return githubUser;
            }catch (IOException e){

            }
            return null;

        }

}
