package com.yxy.pet.service.impl;

import com.sun.tools.javac.util.List;
import com.yxy.pet.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.redis.connection.BitFieldSubCommands;
@Service
public class UserSignServiceImpl implements UserSignService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean doSign(int uid, Date date) {
        int offset=date.getDay()-1;
        String key=buildSignKey(uid,date);
        return redisTemplate.opsForValue().setBit(key,offset,true);
    }

    @Override
    public boolean checkSign(int uid, Date date) {
        int offset=date.getDay()-1;
        String key=buildSignKey(uid,date);
        return redisTemplate.opsForValue().getBit(key,offset);
    }

    @Override
    public long getSignCount(int uid, Date date) {
        String key=buildSignKey(uid,date);

        Long bitCount = (Long) redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.bitCount(key.getBytes());
        });

        return bitCount != null ? bitCount : 0;
    }

    private String buildSignKey(int uid, Date date) {
        return "u:sign:"+uid+":"+ FormatDate(date,"yyyyMM");
    }
    private static String FormatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


    @Override
    public long GetContinuousSignCount(int uid, Date date) {
        String key=buildSignKey(uid,date);
        int signCount = 0;
        String type = "u" + date.getDay(); // 取1号到当天的签到状态
        /*Object result = redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.bitField(key.getBytes(), BitFieldSubCommands.get(BitFieldSubCommands.BitFieldType.unsigned(date.getDay())))
        );*/
        Object result=null;
        if (result != null) {
            long[] list = (long[]) result;
            if (list.length > 0) {
                long v = list[0];
                for (int i = 0; i < date.getDay(); i++) {
                    if ((v >> 1) << 1 == v) {
                        if (i > 0) break;
                    } else {
                        signCount += 1;
                    }
                    v >>= 1;
                }
            }
        }
        return signCount;
    }


    @Override
    public Date getFirstSignDate(int uid, Date date) {
        String key=buildSignKey(uid,date);
        Long pos = redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.bitPos(key.getBytes(), true));
        return pos < 0 ? null : new Date(date.getTime() - (date.getDay() - pos + 1) * 24 * 60 * 60 * 1000);
    }

    @Override
    public Map<String, Boolean> getSignInfo(int uid, Date date) {
        String key=buildSignKey(uid,date);
        Map<String, Boolean> signMap = new HashMap<>();
        String type = "u" + getDayOfMonth(date);
        Object result=null;
        if (result != null) {
            long[] list = (long[]) result;
            if (list.length > 0) {
                long v = list[0];
                for (int i = getDayOfMonth(date); i > 0; i--) {
                    Date d = new Date(date.getTime() + (i - date.getDay()) * 24 * 60 * 60 * 1000);
                    signMap.put(FormatDate(d, "yyyy-MM-dd"), ((v >> 1) << 1) != v);
                    v >>= 1;
                }
            }
        }
        return signMap;
    }
    private static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        if (month == 2) {
            return 28;
        }
        if (List.of(1, 3, 5, 7, 8, 10, 12).contains(month)) {
            return 31;
        }
        return 30;
    }


}
