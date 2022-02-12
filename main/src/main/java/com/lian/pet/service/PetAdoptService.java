package com.lian.pet.service;

import com.lian.pet.domain.dto.AddPetAdoptDTO;
import com.lian.pet.domain.dto.QueryAdoptDTO;
import com.lian.pet.domain.vo.AdoptAndUserVO;
import com.lian.pet.domain.vo.PetAdoptVO;
import com.lian.pet.domain.vo.PetCountVO;
import java.util.List;

/**
 * @Desc: Pet Adopt Service
 * @Author: Lian
 * @Time: 2022/1/24 15:29
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
     * @return
     */
    AdoptAndUserVO getPetAdoptById(Integer adoptId);

    /**
     * 查询总数
     * @param openId
     * @return
     */
    PetCountVO queryCount(String openId);
}
