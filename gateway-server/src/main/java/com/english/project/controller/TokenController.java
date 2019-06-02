package com.english.project.controller;

import com.english.project.feign.Oauth2Client;
import com.english.project.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZhuQing
 * @Date: 2019/2/1 15:26
 */
@RestController
public class TokenController {

    private static final Logger LOGGER=LoggerFactory.getLogger(TokenController.class);


    @Autowired
    private Oauth2Client oauth2Client;

    private static final String CLIENT_ID     = "system";
    private static final String CLIENT_SECRET = "system";
    private static final String CLIENT_SCOPE  = "app";

    /**
     * 系统登陆<br>
     * 根据用户名登录<br>
     * 采用oauth2密码模式获取access_token和refresh_token
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/sys/login")
    public ResultVo<Object> login(String userName, String password) {
        LOGGER.info("用户名密码登录");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "password");
        parameters.put("client_id", CLIENT_ID);
        parameters.put("client_secret", CLIENT_SECRET);
        parameters.put("scope", CLIENT_SCOPE);
        parameters.put("username", userName);
        parameters.put("password", password);

        Map<String, Object> tokenInfo = oauth2Client.postAccessToken(parameters);
        return new ResultVo<>(200,"success",tokenInfo);
    }

    /**
     * 刷新token
     *
     * @param refresh_token
     * @return
     */
//    public ResultVo<Object> refresh_token(String refresh_token) {
//        LOGGER.info("刷新token");
//        Map<String, String> parameters = new HashMap<>();
////        parameters.put(OAuth2Utils.GRANT_TYPE, "refresh_token");
////        parameters.put(OAuth2Utils.CLIENT_ID, CLIENT_ID);
////        parameters.put("client_secret", CLIENT_SECRET);
////        parameters.put(OAuth2Utils.SCOPE, CLIENT_SCOPE);
////        parameters.put("refresh_token", refresh_token);
//        return new ResultVo<Object>(200,"success",oauth2Client.postAccessToken(parameters));
//    }

    /**
     *  退出
     *
     * @param access_token
     */
    @GetMapping("/sys/logout")
    public ResultVo<?> logout(String access_token) {
        LOGGER.info("退出");
        oauth2Client.removeToken(access_token);
        return new ResultVo(200,"success");
    }
}
