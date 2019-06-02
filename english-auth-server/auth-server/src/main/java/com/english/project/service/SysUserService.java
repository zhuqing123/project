package com.english.project.service;

import com.english.project.dto.SysUserDto;
import com.english.project.entity.SysUser;
import com.english.project.vo.ResultVo;
import com.english.project.vo.SysUserVo;

/**
 * @Author ZhuQing
 * @Date: 2019/4/16  10:41
 */
public interface SysUserService {

    /**
     * 用户注册
     * @param sysUserDto
     * @return
     */
    ResultVo register(SysUserDto sysUserDto);

    /**
     * 通过登录名去查找用户
     * @param loginName
     * @return
     */
    SysUserVo findByLoginName(String loginName);

    /**
     * 通过userName去查找用户
     * @param userName
     * @return
     */
    SysUser findByUserName(String userName);
}
