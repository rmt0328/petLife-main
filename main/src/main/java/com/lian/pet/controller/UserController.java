package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.WxGetPhoneDTO;
import com.lian.pet.domain.dto.WxUserDTO;
import com.lian.pet.domain.vo.WxPhoneVO;
import com.lian.pet.domain.vo.WxUserVO;
import com.lian.pet.service.WxUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc: User Controller
 * @Author: Lian
 * @Time: 2022/1/11 11:25
 */
@Slf4j
@RequestMapping("user")
@RestController
@AllArgsConstructor
public class UserController {
    private final WxUserService wxUserService;

    @PostMapping("/login")
    public AppResp<WxUserVO> getOpenId(@RequestBody WxUserDTO req) {
        log.info("[==========进入user/login方法==========]");
        log.info("微信授权登录");
        log.info("code值：{}", req.getCode());
        WxUserVO wxUserVO = wxUserService.login(req.getCode(), req);
        return AppResp.succeed(wxUserVO);
    }

    /**
     * 获取手机号
     * @param req
     * @return
     */
    @PostMapping("/getPhoneNumber")
    public AppResp<WxPhoneVO> getPhone(@RequestBody WxGetPhoneDTO req) {
        WxPhoneVO wxPhoneVO = wxUserService.getPhoneNumber(req);
        return AppResp.succeed(wxPhoneVO);
    }

    /**
     * 查询用户信息 by openId
     * @param openId
     * @return
     */
    @GetMapping("/getUserInfo")
    public AppResp<WxUserVO> getPhone(@RequestParam String openId) {
        return AppResp.succeed(wxUserService.getUserInfo(openId));
    }

}
