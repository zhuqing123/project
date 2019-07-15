package com.sxlc.project.controller;

import com.sxlc.project.enums.ResultEnum;
import com.sxlc.project.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author : Andy
 * @ Date : Cteated in 14:54 2019/6/17
 * @ Description : 登录处理
 * @ Version : v1.0
 */
@Api(tags = {"登录处理器"})
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation(value = "退出登录")
    @DeleteMapping("/logout")
    public ResultVo logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = StringUtils.replace(token, OAuth2AccessToken.BEARER_TYPE, "");
        if (this.consumerTokenServices.revokeToken(token)) {
            return new ResultVo(ResultEnum.SUCCESS);
        }
        return new ResultVo(ResultEnum.FAil);
    }
}
