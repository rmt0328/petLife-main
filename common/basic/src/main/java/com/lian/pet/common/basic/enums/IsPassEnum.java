package com.lian.pet.common.basic.enums;

import lombok.Getter;

/**
 * @Desc: 领养申请状态枚举
 * @Author: Lian
 * @Time: 2023/5/18 19:02
 */
public enum IsPassEnum {

    /**
     * 0-未通过 1-通过 2-审核中
     */
    NO_PASS("0", "未通过"),
    PASSED("1", "已通过"),
    IS_AUDIT("2", "审核中");

    @Getter
    private final String code;
    private final String message;

    IsPassEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
