package com.yxy.pet.service;

import com.yxy.pet.domain.dto.AddPetCircleDTO;
import com.yxy.pet.domain.dto.QueryPetCircleDTO;
import com.yxy.pet.domain.vo.PetCircleVO;

import java.util.List;

/**
 * @Desc: Pet Circle Service
 * @Author: yxy
 * @Time: 2022/1/28 22:03
 */
public interface PetCircleService {

    /**
     * 新增动态
     * @param req
     */
    void addPetCircle(AddPetCircleDTO req);

    /**
     * 查询动态列表
     * @param req
     * @return
     */
    List<PetCircleVO> queryPetCircles(QueryPetCircleDTO req);

    /**
     * 查询动态
     * @param id
     * @return
     */
    PetCircleVO getPetCircle(String id);
}
