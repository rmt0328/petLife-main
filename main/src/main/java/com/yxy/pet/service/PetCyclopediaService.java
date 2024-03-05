package com.yxy.pet.service;

import com.yxy.pet.domain.dto.QueryPetCyclopediaDTO;
import com.yxy.pet.domain.vo.PetCyclopediaVO;

import java.util.List;

/**
 * @Desc: Pet Cyclopedia Service
 * @Author: yxy
 * @Time: 2022/1/30 14:10
 */
public interface PetCyclopediaService {

    /**
     * 查询宠物科普列表
     * @param req
     * @return
     */
    List<PetCyclopediaVO> queryPetCyclopedias(QueryPetCyclopediaDTO req);

    /**
     * 查询宠物科普
     * @param id
     * @return
     */
    PetCyclopediaVO getPetCyclopedia(Integer id);
}
