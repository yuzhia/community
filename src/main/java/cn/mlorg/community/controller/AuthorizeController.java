package cn.mlorg.community.controller;

import cn.mlorg.community.dto.AccessTokenDto;
import cn.mlorg.community.dto.GithubUser;
import cn.mlorg.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/oauth/redirect")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("003d12d5e80faf06ff1b");
        accessTokenDto.setClient_secret("52f42d952790d17ca6e3bc97caca9d7183e9e2bb");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/oauth/redirect");
        accessTokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getLogin());
        return "index";
    }
}
