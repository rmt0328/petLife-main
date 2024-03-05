package com.yxy.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/27 18:25
 */
@Data
@Builder
@ApiModel("")
public class PetFindAndUserVO {
    @ApiModelProperty(value = "寻宠对象")
    private PetFindVO petFindVO;
    @ApiModelProperty(value = "用户对象")
    private WxUserVO wxUserVO;

}
