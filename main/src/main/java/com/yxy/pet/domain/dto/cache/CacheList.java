package com.yxy.pet.domain.dto.cache;

import com.yxy.pet.domain.vo.PetAdoptVO;
import com.yxy.pet.domain.vo.PetCircleVO;
import com.yxy.pet.domain.vo.PetFindVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * @Desc: 存储到缓存中的数据对象
 * @Author: yxy
 * @Time: 2022/2/14 17:02
 */
@AllArgsConstructor
@Data
@Builder
public class CacheList {
    private List<PetCircleVO> petCircleVOS;
    private List<PetAdoptVO> petAdoptVOS;
    private List<PetFindVO> petFindVOS;
}
