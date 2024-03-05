package com.yxy.pet.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * apply_adopt
 * @author 
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("adopt_apply")
public class AdoptApply implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 宠物领养ID
     */
    private Integer adoptId;

    /**
     * 宠物领养发布者ID
     */
    private String adoptUserId;

    /**
     * 用户性别（1-男 2-女）
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 养宠数量
     */
    private Integer petNum;

    /**
     * 住房情况（1-租房 2-自有房）
     */
    private String houseStatus;

    /**
     * 婚姻状况（1-未婚 2-已婚）
     */
    private String maritalStatus;

    /**
     * 收入（1-八千以内 2-八千到两万 3-两万以上）
     */
    private String earning;

    /**
     * 职业
     */
    private String job;

    /**
     * 居住地
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信号
     */
    private String weixin;

    /**
     * 申请理由
     */
    private String content;

    /**
     * 0-未通过 1-通过 2-审核中
     */
    private String isPassed;

    /**
     * 0-正常 1-删除
     */
    @TableLogic(value = "0", delval = "1")
    private String isDeleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}