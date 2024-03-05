package com.yxy.pet.domain.dto;

import com.yxy.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/30 14:19
 */
@Data
@ApiModel("查询宠物科普")
public class QueryPetCyclopediaDTO extends BasePageQueryReq {
}
