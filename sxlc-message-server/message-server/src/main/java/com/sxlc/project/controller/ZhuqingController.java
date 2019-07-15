package com.sxlc.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ZhuQing
 * @Date: 2019/5/26  20:07
 */
@RestController
@RequestMapping("/zhuqing")
public class ZhuqingController {


    @GetMapping("/test")
    public Object test(HttpServletRequest request) {
        return null;
    }
}
