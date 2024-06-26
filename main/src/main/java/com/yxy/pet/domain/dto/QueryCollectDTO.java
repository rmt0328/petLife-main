package com.yxy.pet.domain.dto;

import com.yxy.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/2/18 13:47
 */
@Data
@ApiModel("查询收藏")
public class QueryCollectDTO extends BasePageQueryReq {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "类型", notes = "ADOPT FIND CIRCLE")
    private String type;
}
