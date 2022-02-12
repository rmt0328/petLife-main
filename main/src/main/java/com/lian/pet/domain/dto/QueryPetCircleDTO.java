package com.lian.pet.domain.dto;

import com.lian.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Desc:
 * @Author: Lian
 * @Time: 2022/1/25 18:23
 */
@Data
@ApiModel("查询宠物圈")
public class QueryPetCircleDTO extends BasePageQueryReq {
    private String openId;
}
