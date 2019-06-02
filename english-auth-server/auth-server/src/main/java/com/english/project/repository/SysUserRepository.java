package com.english.project.repository;

import com.english.project.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author ZhuQing
 * @Date: 2019/4/14  22:45
 */
public interface SysUserRepository extends JpaRepository<SysUser,Long>,JpaSpecificationExecutor<SysUser> {

    SysUser findByLoginNameAndDelFlag(String loginName,Byte delFlag);

    SysUser findByUserName(String userName);

    SysUser findByLoginNameOrUserNameAndDelFlag(String loginName,String loginName1,Byte delFlag);
}
