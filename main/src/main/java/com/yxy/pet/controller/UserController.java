package com.yxy.pet.controller;

import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.dto.WxGetPhoneDTO;
import com.yxy.pet.domain.dto.WxUserDTO;
import com.yxy.pet.domain.vo.WxPhoneVO;
import com.yxy.pet.domain.vo.WxUserVO;
import com.yxy.pet.service.WxUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Desc: User Controller
 * @Author: yxy
 * @Time: 2022/1/11 11:25
 */
@Slf4j
@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final WxUserService wxUserService;

    @PostMapping("/login")
    public AppResp<WxUserVO> getOpenId(@RequestBody WxUserDTO req, HttpSession session) {
        log.info("[==========进入user/login方法==========]");
        log.info("微信授权登录");
        log.info("code值：{}", req.getCode());
        WxUserVO wxUserVO = wxUserService.login(req.getCode(), req);
        if (wxUserVO != null) {
            session.setAttribute("currentUser", wxUserVO);
            log.info("用户信息已存储在会话中");
        }
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

    /**
     * 手机号码登录
     * TODO 密码验证暂未做
     * @param phone
     * @param password
     * @return
     */
    @GetMapping("/phoneLogin")
    public AppResp<WxUserVO> phoneLogin(@RequestParam("phone") String phone,
                                        @RequestParam("password") String password) {
        return AppResp.succeed(wxUserService.phoneLogin(phone, password));
    }

    @PostMapping("/updateUser")
    public AppResp<Boolean> updateUser(@RequestBody WxUserDTO req) {
        return AppResp.succeed(wxUserService.updateUser(req));
    }

}
