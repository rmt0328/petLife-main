package com.yxy.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc: 接收到的申请 VO
 * @Author: yxy
 * @Time: 2022/4/30 12:43
 */
@Data
@ApiModel("申请领养")
@Builder
public class ReceiveApplyVO {

    @ApiModelProperty(value = "ID")
    private Integer id;
    
    @ApiModelProperty(value = "0-未通过 1-通过 2-审核中")
    private String isPassed;

    @ApiModelProperty(value = "名称")
    private String adoptName;

    @ApiModelProperty(value = "申请人")
    private String userName;

    @ApiModelProperty(value = "申请时间")
    private String applyTime;

}
