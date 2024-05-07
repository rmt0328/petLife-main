package com.yxy.pet.service;

import java.util.Date;
import java.util.Map;

public interface UserSignService {
    //用户签到
    boolean doSign(int uid,Date date);

    //检查用户是否签到
    boolean checkSign(int uid,Date date);

    //获取用户签到次数
    long getSignCount(int uid,Date date);

    //获取当月连续签到次数
    long GetContinuousSignCount(int uid,Date date);

    //获取当月首次签到日期
    Date getFirstSignDate(int uid,Date date);

    //获取本月签到情况
    Map<String,Boolean> getSignInfo(int uid,Date date);
}
