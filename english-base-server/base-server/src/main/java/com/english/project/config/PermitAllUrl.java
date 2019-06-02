package com.english.project.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZhuQing
 * @Date: 2019/2/1 14:26
 * 需要放开权限的url
 */
public final class PermitAllUrl {
    /**
     * 监控中心和swagger需要访问的url
     */
    public static final String[] ENDPOINTS = {
            "/druid/**", "/oauth/**",
            "/sys/login", "/swagger-ui.html","/v2/api-docs",
            "/swagger-resources/**","/sysUser/register"
    };

    /**
     * 需要放开权限的url
     *
     * @param urls 自定义的urlF
     * @return 自定义的url和监控中心需要访问的url集合
     */
    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS;
        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[set.size()]);
    }

}
