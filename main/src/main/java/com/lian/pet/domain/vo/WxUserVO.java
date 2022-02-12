package com.lian.pet.domain.vo;

import com.lian.pet.domain.entity.WxUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Desc:
 * @Author: Lian
 * @Time: 2022/1/13 14:10
 */
@Data
@Builder
public class WxUserVO {
    private String openId;
    private String sessionKey;
    private String unionId;
    private String nickname;
    private String avatarUrl;
    private String city;
    private String country;
    private String province;
    private String gender;
    private String phone;
    private String email;
    @ApiModelProperty(value = "进行中数量")
    private Integer doingNum;
    @ApiModelProperty(value = "已完成数量")
    private Integer doneNum;

    public static WxUserVO fromWxUser(WxUser entity) {
        return WxUserVO.builder()
                .nickname(entity.getNickName())
                .avatarUrl(entity.getAvatarUrl())
                .city(entity.getCity())
                .country(entity.getCountry())
                .province(entity.getProvince())
                .gender(entity.getGender())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .openId(entity.getOpenId())
                .sessionKey(entity.getSessionKey())
                .unionId(entity.getUnionId())
                .build();
    }

}
