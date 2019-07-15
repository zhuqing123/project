package com.sxlc.project.service;

import com.sxlc.project.dto.EmailEnclosureDto;
import com.sxlc.project.dto.EmailTextDto;
import com.sxlc.project.exception.CustomException;
import com.sxlc.project.vo.ResultVo;

/**
 * @ Author : Andy
 * @ Date : Cteated in 15:45 2019/6/18
 * @ Description :
 * @ Version :
 */
public interface EmailService {

    /**
     * 发送普通文本邮件
     *
     * @param dto
     * @return
     */
    ResultVo sendEmailText(EmailTextDto dto) throws CustomException;

    /**
     * 发送带附件的邮件
     *
     * @param dto
     * @return
     */
    ResultVo sendEmailEnclosure(EmailEnclosureDto dto) throws CustomException;
}
