package com.sxlc.project.dto;

import com.sxlc.project.constant.RegexpConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author : Andy
 * @ Date : Cteated in 15:31 2019/6/18
 * @ Description : 发送文本邮件实体
 * @ Version : v1.0
 */
@ApiModel(description = "发送普通文本文件实体类")
@Data
public class EmailTextDto implements Serializable {

    @NotNull(message = "邮件地址不能为空")
    @Size(min = 1, max = 10, message = "最多发送10个人")
    @ApiModelProperty(value = "邮件接收者（" + RegexpConstant.EMAIL_REX + "）")
    private List<String> recipients = new ArrayList<>();

    @Size(max = 10, message = "最多抄送10个人")
    @ApiModelProperty(value = "邮件抄送者（" + RegexpConstant.EMAIL_REX + "）")
    private List<String> alternatives = new ArrayList<>();

    @NotNull(message = "主题不能为空")
    @Size(min = 1, max = 30, message = "主题最多30个字符")
    @ApiModelProperty(value = "邮件主题（最多30个字符）")
    private String subject;

    @NotNull(message = "邮件内容不为空")
    @Size(min = 1, max = 500, message = "邮件内容不能超过500个字符")
    @ApiModelProperty(value = "邮件内容（不能超过500个字符）")
    private String context;
}
