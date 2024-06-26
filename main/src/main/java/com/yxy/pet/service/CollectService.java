package com.yxy.pet.service;

import com.yxy.pet.domain.dto.AddCollectDTO;
import com.yxy.pet.domain.dto.QueryCollectDTO;

import java.util.List;

/**
 * @Desc: Collect Service
 * @Author: yxy
 * @Time: 2022/2/18 13:46
 */
public interface CollectService {

    /**
     * 添加收藏
     * @param req
     * @return
     */
    Boolean addCollect(AddCollectDTO req);

    /**
     * 取消收藏
     * @param req
     * @return
     */
    Boolean removeCollect(AddCollectDTO req);

    /**
     * 是否已收藏
     * @param req
     * @return
     */
    Boolean isCollect(AddCollectDTO req);

    /**
     * 查询收藏
     * @param req
     * @param <T>
     * @return
     */
    <T> List<T> queryCollect(QueryCollectDTO req);
}
