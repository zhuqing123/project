package com.sxlc.project.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @ Author : Andy
 * @ Date : Cteated in 17:47 2019/6/18
 * @ Description : 发送带附件的邮件实体
 * @ Version : v1.0
 */
@Data
public class EmailEnclosureDto extends EmailTextDto {

    @NotNull(message = "附件不能为空")
    @Size(min = 1, max = 5, message = "上传附件不能超过5个")
    private List<MultipartFile> files;
}
