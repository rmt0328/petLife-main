package com.yxy.pet.controller;

import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.vo.CreditsVO;
import com.yxy.pet.service.CreditsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc: Credits Controller
 * @Author: yxy
 * @Time: 2022/1/30 16:57
 */
@Slf4j
@RequestMapping("credits")
@RestController
@AllArgsConstructor
public class CreditsController {
    private final CreditsService creditsService;

    /**
     * 查询积分
     * @param openId
     * @return
     */
    @RequestMapping("/getCredits")
    public AppResp<CreditsVO> getCredits(@RequestParam("openId") String openId){
        return AppResp.succeed(creditsService.getCredits(openId));
    }

    /**
     * 更新积分/签到天数
     * @param openId
     * @param creditsNum
     * @return
     */
    @RequestMapping("/updateCredits")
    public AppResp<Integer> updateCredits(@RequestParam("openId") String openId,
                                       @RequestParam("creditsNum") Integer creditsNum){
        return AppResp.succeed(creditsService.updateCredits(openId, creditsNum));
    }
}
