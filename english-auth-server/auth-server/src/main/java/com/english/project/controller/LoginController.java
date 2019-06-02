package com.english.project.controller;

import com.english.project.enums.ResultEnum;
import com.english.project.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ZhuQing
 * @Date: 2019/6/2  15:11
 */
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @DeleteMapping("/logouting")
    public ResultVo logouting(@RequestParam("token") String token) {
        if (this.consumerTokenServices.revokeToken(token)) {
            LOGGER.debug("退出成功");
            return new ResultVo(ResultEnum.SUCCESS);
        }

        LOGGER.debug("退出失败");
        return new ResultVo(-1, "退出失败");
    }

}
