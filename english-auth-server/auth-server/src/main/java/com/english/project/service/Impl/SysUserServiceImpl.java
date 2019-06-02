package com.english.project.service.Impl;

import com.english.project.constant.Constant;
import com.english.project.dto.SysUserDto;
import com.english.project.entity.SysUser;
import com.english.project.enums.ResultEnum;
import com.english.project.repository.SysUserRepository;
import com.english.project.service.SysUserService;
import com.english.project.vo.ResultVo;
import com.english.project.vo.SysUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author ZhuQing
 * @Date: 2019/4/16  10:42
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public ResultVo register(SysUserDto sysUserDto) {


        SysUser sysUser = this.sysUserRepository.findByLoginNameOrUserNameAndDelFlag(sysUserDto.getLoginName(), sysUserDto.getLoginName(), Constant.DEL_FLAG_ZERO);
        if (null != sysUser) {
            return new ResultVo(ResultEnum.LOGIN_NAME_EXIST);
        }

        sysUser = this.sysUserRepository.findByLoginNameOrUserNameAndDelFlag(sysUserDto.getUserName(), sysUserDto.getUserName(), Constant.DEL_FLAG_ZERO);
        if (null != sysUser) {
            return new ResultVo(ResultEnum.USER_NAME_EXIST);
        }

        sysUser = new SysUser();
        sysUserDto.setPassword(this.bCryptPasswordEncoder.encode(sysUserDto.getPassword()));
        BeanUtils.copyProperties(sysUserDto, sysUser);
        this.sysUserRepository.save(sysUser);
        return new ResultVo(ResultEnum.SUCCESS);
    }

    @Override
    public SysUserVo findByLoginName(String loginName) {
        SysUser sysUser = this.sysUserRepository.findByLoginNameAndDelFlag(loginName, Constant.DEL_FLAG_ZERO);
        SysUserVo sysUserVo = null;
        if (null != sysUser) {
            sysUserVo = new SysUserVo();
            BeanUtils.copyProperties(sysUser, sysUserVo);
        }
        return sysUserVo;
    }

    @Override
    public SysUser findByUserName(String userName) {
        return this.sysUserRepository.findByUserName(userName);
    }
}
