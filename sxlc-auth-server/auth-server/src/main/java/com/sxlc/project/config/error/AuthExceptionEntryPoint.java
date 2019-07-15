package com.sxlc.project.config.error;

import com.alibaba.fastjson.JSON;
import com.sxlc.project.vo.ResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ZhuQing
 * @Date: 2019/2/1 14:54
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LOGGER.info("========================================>AuthExceptionEntryPoint", authException);
        ResultVo resultVo = null;
        Throwable cause = authException.getCause();
        if (cause instanceof InvalidTokenException) {
            resultVo = new ResultVo(401, authException.getMessage());
        } else {
            LOGGER.info("");

            resultVo = new ResultVo(401, "AccessDenied");
        }
        //        map.put("data", authException.getMessage());
        //        map.put("success", false);
        //        map.put("path", request.getServletPath());
        //        map.put("timestamp", String.valueOf(new Date().getTime()));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), JSON.toJSON(resultVo));
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
