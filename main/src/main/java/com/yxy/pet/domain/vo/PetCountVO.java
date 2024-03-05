package com.yxy.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/31 1:25
 */
@Data
@Builder
@ApiModel("查询领养/寻宠总数")
public class PetCountVO {
    @ApiModelProperty(value = "领养总数")
    private Integer petAdoptCount;
    @ApiModelProperty(value = "寻宠总数")
    private Integer petFindCount;
}
