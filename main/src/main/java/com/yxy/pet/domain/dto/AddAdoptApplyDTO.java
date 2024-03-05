package com.yxy.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc: 添加申请领养 DTO
 * @Author: yxy
 * @Time: 2022/4/30 12:43
 */
@Data
@ApiModel("添加申请领养")
public class AddAdoptApplyDTO {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "宠物领养ID")
    private Integer adoptId;

    @ApiModelProperty(value = "宠物领养发布者ID")
    private String adoptUserId;

    @ApiModelProperty(value = "用户性别（1-男 2-女）")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "养宠数量")
    private Integer petNum;

    @ApiModelProperty(value = "住房情况（1-租房 2-自有房）")
    private String houseStatus;

    @ApiModelProperty(value = "婚姻状况（1-未婚 2-已婚）")
    private String maritalStatus;

    @ApiModelProperty(value = "收入（1-八千以内 2-八千到两万 3-两万以上）")
    private String earning;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "居住地")
    private String address;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "微信号")
    private String weixin;

    @ApiModelProperty(value = "申请理由")
    private String content;

}
