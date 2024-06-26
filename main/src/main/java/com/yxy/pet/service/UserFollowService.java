package com.yxy.pet.service;

import com.yxy.pet.domain.dto.AddUserFollowDTO;
import com.yxy.pet.domain.vo.UserFollowVO;

import java.util.List;

/**
 * @Desc: UserFollow Service
 * @Author: yxy
 * @Time: 2022/2/12 15:41
 */
public interface UserFollowService {

    /**
     * 添加关注
     * @param req
     * @return
     */
    Boolean addUserFollow(AddUserFollowDTO req);

    /**
     * 取消关注
     * @param req
     * @return
     */
    Boolean unUserFollow(AddUserFollowDTO req);

    /**
     * 是否关注
     * @param userId
     * @param followId
     * @return
     */
    Boolean isFollow(String userId, String followId);

    /**
     * 查询关注好友
     * @param userId
     * @return
     */
    List<UserFollowVO> queryUserFollows(String userId);

}
