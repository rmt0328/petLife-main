package com.yxy.pet.domain.dto;

import com.yxy.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/25 18:23
 */
@Data
@ApiModel("查询领养")
public class QueryAdoptDTO extends BasePageQueryReq {
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "城市")
    private String city;
}
