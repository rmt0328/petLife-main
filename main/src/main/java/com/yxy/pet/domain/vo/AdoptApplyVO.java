package com.yxy.pet.domain.vo;

import com.yxy.pet.domain.entity.AdoptApply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Desc: 申请领养 VO
 * @Author: yxy
 * @Time: 2022/4/30 12:43
 */
@Data
@Builder
@ApiModel("申请领养")
public class AdoptApplyVO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "宠物领养ID")
    private Integer adoptId;

    @ApiModelProperty(value = "宠物领养标题")
    private String adoptTitle;

    @ApiModelProperty(value = "宠物领养发布者ID")
    private Integer adoptUserId;

    @ApiModelProperty(value = "用户性别（1-男 2-女）")
    private String sex;

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

    @ApiModelProperty(value = "0-未通过 1-通过 2-审核中")
    private String isPassed;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "姓名")
    private String userName;

    public static AdoptApplyVO fromAdoptApply(AdoptApply entity) {
        AdoptApplyVO adoptApplyVO = AdoptApplyVO.builder().build();
        BeanUtils.copyProperties(entity, adoptApplyVO);
        // TODO 使用字典
        adoptApplyVO.setSex("1".equals(adoptApplyVO.getSex()) ? "男" : "女");
        adoptApplyVO.setHouseStatus("1".equals(adoptApplyVO.getHouseStatus()) ? "租房" : "自有房");
        adoptApplyVO.setMaritalStatus("1".equals(adoptApplyVO.getMaritalStatus()) ? "未婚" : "已婚");
        adoptApplyVO.setEarning("1".equals(adoptApplyVO.getEarning()) ? "八千以内" : "2".equals(adoptApplyVO.getEarning()) ? "八千到两万" : "两万以上");
        return adoptApplyVO;
    }

}
