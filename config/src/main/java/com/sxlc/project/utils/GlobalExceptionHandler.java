package com.sxlc.project.utils;

import com.sxlc.project.enums.ResultEnum;
import com.sxlc.project.exception.CustomException;
import com.sxlc.project.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @ Author : Andy
 * @ Date : Cteated in 13:35 2019/6/17
 * @ Description : 全局异常处理
 * @ Version : v1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResultVo exceptionHandler(Exception e) {
        LOGGER.error("系统全局异常处理", e);
        ResultVo vo = new ResultVo(ResultEnum.FAil);
        if (e instanceof BindException) {
            BindException e1 = (BindException) e;
            vo.setCode(-1);
            BindingResult b = e1.getBindingResult();
            List<FieldError> list = b.getFieldErrors();
            if (!list.isEmpty()) {
                FieldError f = list.get(0);
                vo.setMsg(f.getDefaultMessage());
            }
            return vo;
        } else if (e instanceof CustomException) {
            CustomException e1 = (CustomException) e;
            vo.setCode(-1);
            vo.setMsg(e1.getMessage());
            return vo;
        }
        return vo;
    }

}
