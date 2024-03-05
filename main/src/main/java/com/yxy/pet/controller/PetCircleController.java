package com.yxy.pet.controller;

import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.dto.AddPetCircleDTO;
import com.yxy.pet.domain.dto.QueryPetCircleDTO;
import com.yxy.pet.domain.vo.PetCircleVO;
import com.yxy.pet.service.PetCircleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: Pet Circle Controller
 * @Author: yxy
 * @Time: 2022/1/28 22:42
 */
@Slf4j
@RequestMapping("petCircle")
@RestController
@AllArgsConstructor
public class PetCircleController {
    private final PetCircleService petCircleService;

    /**
     * 新增宠物圈
     * @param req
     * @return
     */
    @PostMapping("/add")
    public AppResp<Void> addPetCircle(@RequestBody AddPetCircleDTO req) {
        petCircleService.addPetCircle(req);
        return AppResp.succeed(null);
    }

    /**
     * 查询宠物圈列表
     * @param req
     * @return
     */
    @PostMapping("/queryList")
    public AppResp<List<PetCircleVO>> queryPetCircles(@RequestBody QueryPetCircleDTO req) {
        return AppResp.succeed(petCircleService.queryPetCircles(req));
    }

    @GetMapping("/getById")
    public AppResp<PetCircleVO> queryPetCircles(@RequestParam("id") String id) {
        return AppResp.succeed(petCircleService.getPetCircle(id));
    }

}
