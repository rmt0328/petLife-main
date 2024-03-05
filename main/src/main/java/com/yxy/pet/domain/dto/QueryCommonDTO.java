package com.yxy.pet.domain.dto;

import com.yxy.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/25 18:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryCommonDTO extends BasePageQueryReq {
    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "Z状态")
    private String status;
}
