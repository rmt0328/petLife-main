package com.yxy.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc: 我申请的
 * @Author: yxy
 * @Time: 2022/5/1 21:40
 */
@Data
@Builder
@ApiModel("我申请的")
public class MyApplyVO {

    @ApiModelProperty(value = "申请ID")
    private Integer applyId;

    @ApiModelProperty(value = "adoptId")
    private Integer adoptId;

    @ApiModelProperty(value = "名称")
    private String applyName;

    @ApiModelProperty(value = "申请时间")
    private String applyTime;

    @ApiModelProperty(value = "申请状态 0-未通过 1-通过 2-审核中")
    private String status;
}
