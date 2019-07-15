package com.sxlc.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sxlc.project.constant.Constant;
import com.sxlc.project.constant.RegexpConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author ZhuQing
 * @Date: 2019/5/26  19:08
 */
@ApiModel(description = "注册实体")
@Data
public class SysUserDto implements Serializable {

    @ApiModelProperty(value = "登录名，只能是手机号,正则为" + RegexpConstant.PHONE_REX, required = true)
    @NotNull(message = "请填写登录手机号")
    @Pattern(regexp = RegexpConstant.PHONE_REX, message = Constant.PHONE_REX_ERROR)
    private String loginName;

    @ApiModelProperty(value = "密码，正则为:" + RegexpConstant.PASSWORD_REX, required = true)
    @NotNull(message = "请填写密码")
    @Pattern(regexp = RegexpConstant.PASSWORD_REX, message = Constant.PASSWORD_REX_ERROR)
    private String password;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "请填写用户名")
    private String userName;

    @ApiModelProperty(value = "性别0女1男", required = true, allowableValues = "0,1")
    @NotNull(message = "请填写性别")
    private Byte sex;

    @ApiModelProperty(value = "头像地址", required = false)
    private String avatarUrl;

    @ApiModelProperty(value = "出生日期格式(yyyy-MM-dd)", required = true)
    @NotNull(message = "请填写出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
