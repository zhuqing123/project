package com.english.project.feign;

import com.english.project.vo.ResultVo;
import com.english.project.vo.SysUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author ZhuQing
 * @Date: 2019/5/27  22:28
 */
@FeignClient(name = "auth-server")
public interface SysUserFeign {

    /**
     *
     * @param loginName
     * @return
     */
    @GetMapping("/sysUser/loginName/{loginName}")
    ResultVo<SysUserVo> findByLoginName(@PathVariable("loginName") String loginName);
}

@Component
class SysUserFeignImpl implements SysUserFeign{

    @Override
    public ResultVo<SysUserVo> findByLoginName(String loginName) {
        return null;
    }
}
