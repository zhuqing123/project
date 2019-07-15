package com.sxlc.project.feign;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author : Andy
 * @ Date : Cteated in 15:02 2019/6/14
 * @ Description : 登录远程调用
 * @ Version : v1.0
 */
@FeignClient(name = "auth-server", fallbackFactory = Oauth2ClientHystrixFeignFallBackFactory.class)
public interface Oauth2Client {

    @PostMapping(path = "/oauth/token")
    Map<String, Object> postAccessToken(@RequestParam Map<String, String> parameters);


}


@Component
class Oauth2ClientHystrixFeignFallBackFactory implements FallbackFactory<Oauth2Client> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Oauth2ClientHystrixFeignFallBackFactory.class);


    @Override
    public Oauth2Client create(Throwable throwable) {
        LOGGER.error("fallback; reason was: {}", throwable);
        return new Oauth2Client() {

            @Override
            public Map<String, Object> postAccessToken(Map<String, String> parameters) {
                Map<String, Object> map = new HashMap<String, Object>(2);
                if (throwable instanceof HystrixTimeoutException) {
                    map.put("code", -1);
                    map.put("msg", "授权服务超时");
                } else {
                    map.put("code", -1);
                    map.put("msg", "用户名或密码错误请重新登录");
                }
                return map;
            }
        };
    }
}
