package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.dto.AddChatDTO;
import com.lian.pet.domain.entity.Chat;
import com.lian.pet.domain.entity.WxUser;
import com.lian.pet.domain.vo.ChatCountVO;
import com.lian.pet.domain.vo.ChatVO;
import com.lian.pet.factory.ResponseBeanFactory;
import com.lian.pet.mapper.ChatMapper;
import com.lian.pet.mapper.WxUserMapper;
import com.lian.pet.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Desc:
 * @Author: Lian
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

    @Override
    public List<ChatCountVO> queryChatCount(String userId) {
        List<Chat> chats = chatMapper.queryChatsOrderBy(userId);
        if (chats.isEmpty()) {
            log.info("未查到消息,userId={}", userId);
            return null;
        }
        List<ChatCountVO> chatCountVOS = new ArrayList<>();
        chats.forEach(chat -> {
            // 排除自己
            if (userId.equals(chat.getFromId())) {
                return;
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
                    .latestContent(chats.get(0).getContent())
                    .userId(wxUser.getOpenId())
                    .nickname(wxUser.getNickName())
                    .avatarUrl(wxUser.getAvatarUrl())
                    .time(DateUtil.dateToString(wxUser.getCreateTime()).substring(0, 10))
                    .build();
            chatCountVOS.add(chatCountVO);
        });
        log.info("执行成功[查询聊天消息数量]");
        return chatCountVOS;
    }

    @Override
    public List<ChatVO> queryChatContent(String userId1, String userId2) {
        List<Chat> chats = chatMapper.queryChatsByUserId(userId1, userId2);
        if (chats.isEmpty()) {
            log.info("暂无聊天记录");
            return Collections.emptyList();
        }
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
            // 对方阅读后 状态修改为已读
            if (!userId1.equals(chat.getFromId()) && "0".equals(chat.getIsDone())) {
                chat.setIsDone("1");
                chatMapper.updateById(chat);
            }
        });
        log.info("执行成功[查询聊天详情]");
        return chatVOS;
    }

    @Override
    public Integer queryUnreadTotal(String userId) {
        Integer count = chatMapper.selectCount(Wrappers.<Chat>lambdaQuery()
                .eq(Chat::getToId, userId)
                .eq(Chat::getIsDone, "0"));
        log.info("执行成功[查询未读消息总数]");
        return count;
    }
}
