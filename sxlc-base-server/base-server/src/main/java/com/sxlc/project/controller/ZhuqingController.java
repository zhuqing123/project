package com.sxlc.project.controller;

import com.sxlc.project.feign.SysUserFeign;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysUserFeign sysUserFeign;

    @GetMapping("/test")
    public Object test(HttpServletRequest request) {
        return this.sysUserFeign.findByLoginName("18382431531");
    }
}
