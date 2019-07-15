package com.sxlc.project.controller;

import com.sxlc.project.constant.RegexpConstant;
import com.sxlc.project.dto.EmailEnclosureDto;
import com.sxlc.project.dto.EmailTextDto;
import com.sxlc.project.enums.ResultEnum;
import com.sxlc.project.exception.CustomException;
import com.sxlc.project.service.EmailService;
import com.sxlc.project.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @ Author : Andy
 * @ Date : Cteated in 15:12 2019/6/18
 * @ Description : 邮件发送
 * @ Version : v1.0
 */
@Api(tags = {"邮件发送"})
@RestController
@RequestMapping("/email")
public class EmailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "文本邮件发送")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "文本邮件传输内容", paramType = "body", dataTypeClass = EmailTextDto.class, required = true)
    })
    @PostMapping("/text")
    public ResultVo sendEmailText(@Validated @RequestBody EmailTextDto dto) throws CustomException {
        if (this.remveErrorEmail(dto.getRecipients(), dto.getAlternatives())) {
            return new ResultVo(ResultEnum.EMAIL_ERROR);
        }
        return emailService.sendEmailText(dto);
    }

    @ApiOperation(value = "带附件的邮件发送")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "文本邮件传输内容", dataTypeClass = EmailEnclosureDto.class, required = true)
    })
    @PostMapping("/enclosure")
    public ResultVo sendEmailEnclosure(@Validated EmailEnclosureDto dto) throws CustomException {
        if (this.remveErrorEmail(dto.getRecipients(), dto.getAlternatives())) {
            return new ResultVo(ResultEnum.EMAIL_ERROR);
        }
        return this.emailService.sendEmailEnclosure(dto);
    }

    /**
     * 排除不符合规则邮件
     *
     * @param recipients
     * @param alternatives
     * @return
     */
    private boolean remveErrorEmail(List<String> recipients, List<String> alternatives) {
        Pattern pattern = Pattern.compile(RegexpConstant.EMAIL_REX);
        recipients.removeIf(recipient -> !pattern.matcher(recipient).matches());
        if (CollectionUtils.isEmpty(recipients)) {
            return true;
        }
        if (!CollectionUtils.isEmpty(alternatives)) {
            alternatives.removeIf(alternative -> !pattern.matcher(alternative).matches());
        }
        return false;
    }

}
