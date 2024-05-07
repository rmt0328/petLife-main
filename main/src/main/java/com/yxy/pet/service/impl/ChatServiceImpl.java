package com.yxy.pet.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yxy.pet.common.basic.utils.DateUtil;
import com.yxy.pet.domain.dto.AddChatDTO;
import com.yxy.pet.domain.entity.Chat;
import com.yxy.pet.domain.entity.WxUser;
import com.yxy.pet.domain.vo.ChatCountVO;
import com.yxy.pet.domain.vo.ChatVO;
import com.yxy.pet.factory.ResponseBeanFactory;
import com.yxy.pet.mapper.ChatMapper;
import com.yxy.pet.mapper.WxUserMapper;
import com.yxy.pet.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/2/9 10:06
 */
@Slf4j
@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatMapper chatMapper;
    private final WxUserMapper wxUserMapper;

    @Override
    public ChatVO addChat(AddChatDTO req) {
        chatMapper.insert(ResponseBeanFactory.getChat(req));
        log.info("执行成功[新增聊天消息]");
        // 提取最新一条
        List<Chat> chats = chatMapper.selectList(Wrappers.<Chat>lambdaQuery()
                .eq(Chat::getToId, req.getToId())
                .eq(Chat::getFromId, req.getFromId())
                .orderByDesc(Chat::getCreateTime));

        WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery()
                .eq(WxUser::getOpenId, chats.get(0).getFromId()));

        //这段代码的意思就是将新的聊天信息存到数据库中，并返回最新一条的聊天消息的详细信息，以便在前端页面展示最新的聊天信息
        return ChatVO.builder()
                .msgId(chats.get(0).getId())
                .content(chats.get(0).getContent())
                .userId(chats.get(0).getFromId())
                .nickname(wxUser.getNickName())
                .avatarUrl(wxUser.getAvatarUrl())
                // TODO 枚举
                .type("user")
                .time(DateUtil.dateToString(chats.get(0).getCreateTime()))
                .build();
    }

    //用来查询特定用户的最近消息列表以及每个消息对应的未读数量

    @Override
    public List<ChatCountVO> queryChatCount(String userId) {
        //查询有关这个用户的所有消息
        List<Chat> chats = chatMapper.queryChatsOrderBy(userId);
        if (chats.isEmpty()) {
            log.info("未查到消息,userId={}", userId);
            return Collections.emptyList();
        }
        List<ChatCountVO> chatCountVOS = new ArrayList<>();
        chats.forEach(chat -> {
            // 发送者是自己时 调换ID 方便前端展示
            if (userId.equals(chat.getFromId())) {
                String temId = chat.getToId();
                chat.setToId(chat.getFromId());
                chat.setFromId(temId);
            }
            WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery()
                    .eq(WxUser::getOpenId, chat.getFromId()));
            // 查询未读条数
            Integer count = chatMapper.selectCount(Wrappers.<Chat>lambdaQuery()
                    .eq(Chat::getFromId, chat.getFromId())
                    .eq(Chat::getToId, chat.getToId())
                    .eq(Chat::getIsDone, "0"));
            ChatCountVO chatCountVO = ChatCountVO.builder()
                    .count(count)
                    .latestContent(chat.getContent())
                    .userId(wxUser.getOpenId())
                    .nickname(wxUser.getNickName())
                    .avatarUrl(wxUser.getAvatarUrl())
                    .time(DateUtil.dateToString(wxUser.getCreateTime()).substring(0, 10))
                    .build();
            chatCountVOS.add(chatCountVO);
        });
        // 根据userId去重
        return chatCountVOS.stream()
                .filter(distinctByKey(ChatCountVO::getUserId))
                .collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    //查询用户和某个特定用户的聊天消息数量和相关信息
    @Override
    public List<ChatVO> queryChatContent(String userId1, String userId2) {
        //查询这两个用户的聊天记录
        List<Chat> chats = chatMapper.queryChatsByUserId(userId1, userId2);
        if (chats.isEmpty()) {
            log.info("暂无聊天记录");
            return Collections.emptyList();
        }
        //存储处理后的聊天信息
        List<ChatVO> chatVOS = new ArrayList<>();
        chats.forEach(chat -> {
            WxUser wxUser = wxUserMapper.selectOne(Wrappers.<WxUser>lambdaQuery()
                    .eq(WxUser::getOpenId, chat.getFromId()));
            if (ObjectUtils.isEmpty(wxUser)) {
                log.error("未找到此人,openId={}", chat.getFromId());
                return;
            }
            chatVOS.add(ChatVO.builder()
                    .msgId(chat.getId())
                    .content(chat.getContent())
                    .userId(chat.getFromId())
                    .nickname(wxUser.getNickName())
                    .avatarUrl(wxUser.getAvatarUrl())
                    // TODO 枚举
                    .type("user")
                    .time(DateUtil.dateToString(chat.getCreateTime()))
                    .build());
            // 如果当前用户不是消息的发送者且消息状态为未读则更新消息记录状态为已读
            if (!userId1.equals(chat.getFromId()) && "0".equals(chat.getIsDone())) {
                chat.setIsDone("1");
                chatMapper.updateById(chat);
            }
        });
//        log.info("执行成功[查询聊天详情]");
        return chatVOS;
    }
    //用户进入聊天室页面之后就会调用这个方法，
    //方法参数为用户id，根据用户id来查询to为userId的消息，并且这条消息的状态为0（未读）

    @Override
    public Integer queryUnreadTotal(String userId) {
        Integer count = chatMapper.selectCount(Wrappers.<Chat>lambdaQuery()
                .eq(Chat::getToId, userId)
                .eq(Chat::getIsDone, "0"));
//        log.info("执行成功[查询未读消息总数]");
        return count;
    }
}
