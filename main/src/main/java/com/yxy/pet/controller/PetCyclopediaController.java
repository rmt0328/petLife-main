package com.yxy.pet.controller;

import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.dto.QueryPetCyclopediaDTO;
import com.yxy.pet.domain.vo.PetCyclopediaVO;
import com.yxy.pet.service.PetCyclopediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: Pet Cyclopedia Controller
 * @Author: yxy
 * @Time: 2022/1/30 14:39
 */
@Slf4j
@RequestMapping("petCyclopedia")
@RestController
@AllArgsConstructor
public class PetCyclopediaController {
    private final PetCyclopediaService petCyclopediaService;

    /**
     * 查询宠物科普列表
     * @param req
     * @return
     */
    @PostMapping("/queryList")
    public AppResp<List<PetCyclopediaVO>> queryPetCircles(@RequestBody QueryPetCyclopediaDTO req) {
        return AppResp.succeed(petCyclopediaService.queryPetCyclopedias(req));
    }

    /**
     * 查询宠物科普
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public AppResp<PetCyclopediaVO> getPetCircleById(@RequestParam("id") Integer id) {
        return AppResp.succeed(petCyclopediaService.getPetCyclopedia(id));
    }
}
