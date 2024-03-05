package com.yxy.pet.service;

import com.yxy.pet.domain.dto.AddAnswerRecordDTO;
import com.yxy.pet.domain.dto.QueryAnswerRecordsDTO;
import com.yxy.pet.domain.vo.AnswerPrizeVO;
import com.yxy.pet.domain.vo.AnswerRecordsVO;

import java.util.List;

/**
 * @Desc: AnswerPrize Service
 * @Author: yxy
 * @Time: 2022/2/17 14:38
 */
public interface AnswerPrizeService {

    /**
     * 查询题目
     * @param answerId
     * @return
     */
    AnswerPrizeVO getAnswerPrize(String answerId);

    /**
     * 用户答题 存储记录
     * @param req
     * @return
     */
    Boolean addCollectUserAnswer(AddAnswerRecordDTO req);

    /**
     * 今日是否已答题
     * @param userId
     * @return
     */
    String isFinishToday(String userId);

    /**
     * 查询答题记录
     * @param req
     * @return
     */
    List<AnswerRecordsVO> queryAnswerRecords(QueryAnswerRecordsDTO req);

}
