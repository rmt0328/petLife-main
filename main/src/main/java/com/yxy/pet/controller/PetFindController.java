package com.yxy.pet.controller;

import com.yxy.pet.common.basic.response.AppResp;
import com.yxy.pet.domain.dto.AddPetFindDTO;
import com.yxy.pet.domain.dto.QueryPetFindDTO;
import com.yxy.pet.domain.vo.PetFindAndUserVO;
import com.yxy.pet.domain.vo.PetFindVO;
import com.yxy.pet.service.PetFindService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: Pet Find Controller
 * @Author: yxy
 * @Time: 2022/1/24 15:28
 */
@Slf4j
@RequestMapping("petFind")
@RestController
@AllArgsConstructor
public class PetFindController {
    private final PetFindService petFindService;

    /**
     * 添加寻宠信息
     * @param req
     * @return
     */
    @PostMapping("/add")
    public AppResp<Void> addPetAdopt(@RequestBody AddPetFindDTO req) {
        if (ObjectUtils.isEmpty(req.getOpenId())) {
            log.error("终止流程[openId为空]");
            // TODO code值统一定义管理
            return AppResp.failed(104, "openId为空！！");
        }
        petFindService.addPetFind(req);
        return AppResp.succeed(null);
    }

    /**
     * 查询寻宠列表
     * @param req
     * @return
     */
    @PostMapping("/queryList")
    public AppResp<List<PetFindVO>> queryPetFinds(@RequestBody QueryPetFindDTO req) {
        return AppResp.succeed(petFindService.queryPetFindList(req));
    }

    /**
     * 查询寻宠信息
     * @param petFindId
     * @return
     */
    @GetMapping("/getById")
    public AppResp<PetFindAndUserVO> getPetFindById(@RequestParam("adoptId") Integer petFindId) {
        return AppResp.succeed(petFindService.getPetFindById(petFindId));
    }

}
