package com.yxy.pet.service;

import com.yxy.pet.domain.dto.AddChatDTO;
import com.yxy.pet.domain.vo.ChatCountVO;
import com.yxy.pet.domain.vo.ChatVO;

import java.util.List;

/**
 * @Desc: Chat Service
 * @Author: yxy
 * @Time: 2022/2/9 9:48
 */
public interface ChatService {

    /**
     * 新增消息
     * @param req
     * @return
     */
    ChatVO addChat(AddChatDTO req);

    /**
     * 查询消息数量
     * @param userId
     * @return
     */
    List<ChatCountVO> queryChatCount(String userId);

    /**
     * 查询聊天内容
     * @param userId1
     * @param userId2
     * @return
     */
    List<ChatVO> queryChatContent(String userId1, String userId2);

    /**
     * 查询未读消息总数
     * @param userId
     * @return
     */
    Integer queryUnreadTotal(String userId);
}
