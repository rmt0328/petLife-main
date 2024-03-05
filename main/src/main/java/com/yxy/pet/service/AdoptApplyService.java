package com.yxy.pet.service;

import com.yxy.pet.domain.dto.AddAdoptApplyDTO;
import com.yxy.pet.domain.vo.AdoptApplyVO;
import com.yxy.pet.domain.vo.MyApplyVO;
import com.yxy.pet.domain.vo.ReceiveApplyVO;

import java.util.List;

/**
 * @Desc: AdoptApply Service
 * @Author: yxy
 * @Time: 2022/4/30 12:22
 */
public interface AdoptApplyService {

    /**
     * 添加申请领养
     * @param dto
     * @return
     */
    void add(AddAdoptApplyDTO dto);

    /**
     * 查询领养信息
     * @param id
     * @return
     */
    AdoptApplyVO getById(Integer id);

    /**
     * 查询我申请的列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<MyApplyVO> queryMyApplyList(String userId, Integer pageNum, Integer pageSize);

    /**
     * 查询我收到的申请列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<ReceiveApplyVO> queryReceiveApplyList(String userId, Integer pageNum, Integer pageSize);

    /**
     * 通过审核
     * @param applyId
     * @param status 0-驳回 1-通过
     * @return
     */
    boolean audit(Integer applyId, String status);

    /**
     * 查询领养申请未处理消息总数
     * @param userId
     * @return
     */
    Integer applyCount(String userId);

}
