package com.yxy.pet.service;

import com.yxy.pet.domain.dto.AddPetAdoptDTO;
import com.yxy.pet.domain.dto.QueryAdoptDTO;
import com.yxy.pet.domain.dto.QueryAdoptsInDTO;
import com.yxy.pet.domain.vo.AdoptAndUserVO;
import com.yxy.pet.domain.vo.PetAdoptVO;
import com.yxy.pet.domain.vo.PetCountVO;

import java.util.List;

/**
 * @Desc: Pet Adopt Service
 * @Author: yxy
 * @Time: 2023/5/18 19:02
 */
public interface PetAdoptService {

    /**
     * 添加宠物领养
     * @param req
     */
    void addPetAdopt(AddPetAdoptDTO req);

    /**
     * 查询领养列表
     * @param req
     * @return
     */
    List<PetAdoptVO> queryAdoptList(QueryAdoptDTO req);

    /**
     * 获取领养信息
     * @param adoptId
     * @param userId
     * @return
     */
    AdoptAndUserVO getPetAdoptById(Integer adoptId, Integer applyId, String userId);

    /**
     * 查询总数
     * @param openId
     * @return
     */
    PetCountVO queryCount(String openId);

    /**
     * 查询领养列表 in ids
     * @param req
     * @return
     */
    List<PetAdoptVO> queryAdoptsInIds(QueryAdoptsInDTO req);
}
