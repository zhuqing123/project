package com.english.project.service.Impl;

import com.english.project.constant.Constant;
import com.english.project.entity.SysUser;
import com.english.project.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @Author: ZhuQing
 * @Date: 2019/4/2 10:31
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.sysUserRepository.findByLoginNameAndDelFlag(username,Constant.DEL_FLAG_ZERO);
        if (null==sysUser){
            throw new UsernameNotFoundException("用户" + username + "不存在!");
        }
        User user=new User(sysUser.getLoginName(),sysUser.getPassword(),true,true,true,true,new HashSet());
        return user;
    }
}
