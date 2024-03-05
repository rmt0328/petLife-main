package com.yxy.pet.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/22 19:52
 */
@Data
@ApiModel("小程序获取手机号")
public class WxGetPhoneDTO {
    private String sessionKey;
    private String encryptedData;
    private String iv;
}
