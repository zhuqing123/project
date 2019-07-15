package com.sxlc.project.service.Impl;

import com.sxlc.project.dto.EmailEnclosureDto;
import com.sxlc.project.dto.EmailTextDto;
import com.sxlc.project.enums.ResultEnum;
import com.sxlc.project.exception.CustomException;
import com.sxlc.project.service.EmailService;
import com.sxlc.project.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @ Author : Andy
 * @ Date : Cteated in 15:46 2019/6/18
 * @ Description : 邮件发送
 * @ Version : v1.0
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Override
    public ResultVo sendEmailText(EmailTextDto dto) throws CustomException {
        try {
            String[] recipients = new String[dto.getRecipients().size()];
            String[] alternatives = new String[dto.getAlternatives().size()];
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(this.sender);
            simpleMailMessage.setTo(dto.getRecipients().toArray(recipients));
            simpleMailMessage.setCc(dto.getAlternatives().toArray(alternatives));
            simpleMailMessage.setSubject(dto.getSubject());
            simpleMailMessage.setText(dto.getContext());
            this.javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            LOGGER.error("普通邮件发送失败", e);
            throw new CustomException("普通邮件发送失败");
        }
        return new ResultVo(ResultEnum.SUCCESS);
    }

    @Override
    public ResultVo sendEmailEnclosure(EmailEnclosureDto dto) throws CustomException {
        try {
            String[] recipients = new String[dto.getRecipients().size()];
            String[] alternatives = new String[dto.getAlternatives().size()];
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(this.sender);
            helper.setTo(dto.getRecipients().toArray(recipients));
            helper.setCc(dto.getAlternatives().toArray(alternatives));
            helper.setSubject(dto.getSubject());
            helper.setText(dto.getContext());
            List<MultipartFile> files = dto.getFiles();
            for (MultipartFile file : files) {
                helper.addAttachment(file.getOriginalFilename(), file);
            }
            this.javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            LOGGER.error("发送带附件的邮件出现异常", e);
            throw new CustomException("发送带附件的邮件出现异常");
        }
        return new ResultVo(ResultEnum.SUCCESS);
    }
}
