package life.majiang.community2.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community2.dto.AccessTokenDTO;
import life.majiang.community2.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string =  response.body().string();
            String tokenstring = string.split("&")[0];
            String token = tokenstring.split("=")[1];
//            System.out.println(string);
            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public GithubUser getUser(String accsesToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accsesToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string =  response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return null;

    }
}