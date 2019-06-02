package com.english.project.controller;

import com.english.project.dto.SysUserDto;
import com.english.project.enums.ResultEnum;
import com.english.project.service.SysUserService;
import com.english.project.vo.ResultVo;
import com.english.project.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ZhuQing
 * @Date: 2019/4/15  16:33
 */
@Api(tags = {"用户表"})
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户注册")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "用户注册实体", dataTypeClass = SysUserDto.class)
    })
    @PostMapping("/register")
    public ResultVo register(@Validated SysUserDto sysUserDto) {
        return this.sysUserService.register(sysUserDto);
    }

    @ApiOperation(value = "帐号去查找用户名")
    @GetMapping("/loginName/{loginName}")
    public ResultVo<SysUserVo> findByLoginName(@PathVariable String loginName) {
        return new ResultVo(ResultEnum.SUCCESS, this.sysUserService.findByLoginName(loginName));
    }
}
