package com.english.project.controller;

import com.english.project.enums.ResultEnum;
import com.english.project.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author ZhuQing
 * @Date: 2019/6/2  15:11
 */
@RestController
public class LoginController {

    private static final Logger LOGGER=LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    private static final String CLIENT_ID     = "system";
    private static final String CLIENT_SECRET = "app";
    private static final String CLIENT_SCOPE  = "app";

    @PostMapping("/web/login")
    public ResultVo webLogin(String userName, String password) {
        Map<String, String> parameters = new HashMap<String, String>(8) {
            {
                put("grant_type", "password");
                put("client_id", CLIENT_ID);
                put("client_secret", CLIENT_SECRET);
                put("scope", CLIENT_SCOPE);
                put("username", userName);
                put("password", password);
            }
        };
       // Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //Authentication authentication = new UsernamePasswordAuthenticationToken(CLIENT_ID, CLIENT_SECRET,grantedAuthorities);
        try {
            ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = this.tokenEndpoint.postAccessToken(null, parameters);
            return new ResultVo(ResultEnum.SUCCESS,oAuth2AccessTokenResponseEntity.getBody());
        } catch (InvalidGrantException e) {
            LOGGER.error("用户名密码错误",e);
            return new ResultVo(402,"用户名密码错误");
        }catch (Exception e){
            LOGGER.error("login error......",e);
            return new ResultVo(-1,e.getMessage());
        }
    }

    @PostMapping("/logouting")
    public ResultVo logouting(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)){
           String token= StringUtils.removeStart(authorization,"Bearer").trim();
            if (this.consumerTokenServices.revokeToken(token)){
                LOGGER.debug("退出成功");
                return new ResultVo(ResultEnum.SUCCESS);
            }
        }else {
            LOGGER.debug("退出失败");
            return new ResultVo(-1,"非法请求");
        }
        LOGGER.debug("退出失败");
        return new ResultVo(-1,"退出失败");
    }

}
