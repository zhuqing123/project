package com.english.project.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: ZhuQing
 * @Date: 2019/4/2 17:02
 */
@FeignClient(name = "auth-server",fallback = Oauth2ClientImpl.class)
public interface Oauth2Client {

    @PostMapping(path = "/oauth/token")
    Map<String,Object> postAccessToken(@RequestParam Map<String, String> parameters);

    @DeleteMapping(path = "/logouting")
    void removeToken(@RequestParam("token") String token);

}


@Component
class Oauth2ClientImpl implements Oauth2Client{

    private static final Logger LOGGER=LoggerFactory.getLogger(Oauth2ClientImpl.class);

    @Override
    public Map<String, Object> postAccessToken(Map<String, String> parameters) {
        LOGGER.info("登录失败");
        return null;
    }

    @Override
    public void removeToken(String access_token) {

    }
}
