package com.sxlc.project.controller.rpc;

import com.sxlc.project.enums.ResultEnum;
import com.sxlc.project.uidGenerator.impl.CachedUidGenerator;
import com.sxlc.project.vo.ResultVo;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author : Andy
 * @ Date : Cteated in 13:54 2019/6/18
 * @ Description : 百度id生成器
 * @ Version : v1.0
 */
@Api(tags = "百度id生成器")
@RestController
@RequestMapping("/rpc")
public class UidGeneratorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UidGeneratorController.class);

    @Autowired
    private CachedUidGenerator cachedUidGenerator;

    @GetMapping("/uidGenerator")
    public ResultVo uidGenerator() {
        Long uid = this.cachedUidGenerator.getUID();
        return new ResultVo(ResultEnum.SUCCESS, uid);
    }
}
