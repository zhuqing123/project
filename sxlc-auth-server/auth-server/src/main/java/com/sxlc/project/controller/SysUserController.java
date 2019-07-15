package com.sxlc.project.controller;

import com.sxlc.project.enums.ResultEnum;
import com.sxlc.project.vo.ResultVo;
import com.sxlc.project.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ZhuQing
 * @Date: 2019/4/15  16:33
 */
@Api(tags = {"用户表"})
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @ApiOperation(value = "帐号去查找用户名")
    @GetMapping("/loginName/{loginName}")
    public ResultVo<SysUserVo> findByLoginName(@PathVariable String loginName) {
        return new ResultVo(ResultEnum.SUCCESS);
    }
}
