package com.yxy.pet.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 举报信息
 * </p>
 *
 * @author yxy
 * @since 2023-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("complaint")
@ApiModel(value="Complaint对象", description="举报信息")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "举报内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "手机号/微信号")
    @TableField("wechat")
    private String wechat;

    @ApiModelProperty(value = "类型（1-领养人、2-送养人）")
    @TableField("`type`")
    private String type;

    @ApiModelProperty(value = "性别（1-男、2-女、3-未知）")
    @TableField("`sex`")
    private String sex;

    @ApiModelProperty(value = "所在城市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "具体地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "图片")
    @TableField("images")
    private String images;

    @ApiModelProperty(value = "状态（1-审核中、2-已通过、3-未通过）")
    @TableField("`status`")
    private String status;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "0-正常 1-删除")
    @TableLogic(value = "0", delval = "1")
    @TableField("is_deleted")
    private String isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
