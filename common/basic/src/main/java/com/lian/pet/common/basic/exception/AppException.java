package com.lian.pet.common.basic.exception;

/**
 * @Author: Lian
 * @Date: 2021/11/1 12:32
 * @Description: 自定义异常
 */

public class AppException extends RuntimeException {
    private long code;

    public AppException(String message) {
        super(message);
        this.code = AppErrorEnum.UNKNOWN_ERROR.getCode();
    }

    public AppException(long code, String message) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
