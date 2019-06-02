package com.english.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author ZhuQing
 * @Date: 2019/4/14  16:34
 */
@Entity
@Table(name = "sys_user")
@Data
public class SysUser extends BaseEntity {

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 证件号码
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 0女1男
     */
    @Column(name = "sex",columnDefinition = "tinyint(1) 0")
    private Byte sex;

    /**
     * 出生日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday")
    private Date birthday;

    /**
     * 最后登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 头像地址
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

}
