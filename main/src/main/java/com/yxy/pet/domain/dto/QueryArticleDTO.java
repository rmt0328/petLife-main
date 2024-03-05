package com.yxy.pet.domain.dto;

import com.yxy.pet.domain.page.BasePageQueryReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/2/3 16:14
 */
@Data
@ApiModel("查询文章")
public class QueryArticleDTO extends BasePageQueryReq {
    @ApiModelProperty(value = "文章标题")
    private String label;
}
