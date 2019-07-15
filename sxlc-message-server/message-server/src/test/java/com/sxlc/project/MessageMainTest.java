package com.sxlc.project;

import com.sxlc.project.constant.RegexpConstant;
import com.sxlc.project.dto.EmailTextDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @ Author : Andy
 * @ Date : Cteated in 15:56 2019/6/18
 * @ Description : main方法测试
 * @ Version : v1.0
 */
public class MessageMainTest {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(RegexpConstant.EMAIL_REX);
        EmailTextDto emailTextDto = new EmailTextDto();
        System.err.println(pattern.matcher("574728375@qq.com").matches());
        List list = new ArrayList();
        list.add("574728375@qq.com");
        list.add("574728375@qq.com");
        list.add("574728375@qq.com");
        list.add("574728375");
        emailTextDto.setRecipients(list);
        emailTextDto.getRecipients().removeIf(a -> !pattern.matcher((CharSequence) a).matches());
        System.err.println(emailTextDto.getRecipients());
    }
}
