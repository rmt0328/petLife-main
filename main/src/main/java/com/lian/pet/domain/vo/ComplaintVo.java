package com.lian.pet.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 举报信息
 * </p>
 *
 * @author lian
 * @since 2023-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Complaint对象", description="举报信息")
public class ComplaintVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "举报内容")
    private String content;

    @ApiModelProperty(value = "手机号/微信号")
    private String wechat;

    @ApiModelProperty(value = "类型（1-领养人、2-送养人）")
    private String type;
    private String typeName;

    @ApiModelProperty(value = "性别（1-男、2-女、3-未知）")
    private String sex;
    private String sexName;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "具体地址")
    private String address;

    @ApiModelProperty(value = "图片")
    private List<String> images;

    @ApiModelProperty(value = "状态（1-审核中、2-已通过、3-未通过）")
    private String status;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
