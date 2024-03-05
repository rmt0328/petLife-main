package com.yxy.pet.service;

import com.yxy.pet.domain.vo.CreditsVO;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/30 16:32
 */
public interface CreditsService {

    /**
     * 查询积分
     * @param openId
     * @return
     */
    CreditsVO getCredits(String openId);

    /**
     * 积分修改
     * @param openId
     * @param creditsNum
     */
    Integer updateCredits(String openId, Integer creditsNum);
}
