package com.sxlc.project.feign;

import com.sxlc.project.vo.ResultVo;
import com.sxlc.project.vo.SysUserVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ Author : Andy
 * @ Date : Cteated in 15:05 2019/6/14
 * @ Description : 远程调用用户信息查询
 * @ Version : v1.0
 */
public interface SysUserFeign {

    /**
     * @param loginName
     * @return
     */
    @GetMapping("/sysUser/loginName/{loginName}")
    ResultVo<SysUserVo> findByLoginName(@PathVariable("loginName") String loginName);
}

@Component
class SysUserFeignImpl implements SysUserFeign {

    @Override
    public ResultVo<SysUserVo> findByLoginName(String loginName) {
        return null;
    }
}
