package com.yxy.pet.domain.dto;

import com.yxy.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/25 18:23
 */
@Data
@ApiModel("查询宠物圈")
public class QueryPetCircleDTO extends BasePageQueryReq {
    /**
     * 查询用户发布的列表时用到
     */
    private String openId;
}
