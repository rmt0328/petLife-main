package com.lian.pet.controller;

import com.lian.pet.common.basic.exception.AppErrorEnum;
import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.AddAdoptApplyDTO;
import com.lian.pet.domain.dto.AddPetAdoptDTO;
import com.lian.pet.domain.dto.QueryAdoptDTO;
import com.lian.pet.domain.vo.*;
import com.lian.pet.service.AdoptApplyService;
import com.lian.pet.service.PetAdoptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Desc: Pet Adopt Controller
 * @Author: Lian
 * @Time: 2022/1/24 15:28
 */
@Slf4j
@RequestMapping("adopt")
@RestController
@AllArgsConstructor
public class PetAdoptController {
    private final PetAdoptService petAdoptService;
    private final AdoptApplyService adoptApplyService;

    /**
     * 添加宠物领养
     * @param req
     * @return
     */
    @PostMapping("/add")
    public AppResp<Void> addPetAdopt(@RequestBody AddPetAdoptDTO req) {
        if (ObjectUtils.isEmpty(req.getOpenId())) {
            log.error("终止流程[openId为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        petAdoptService.addPetAdopt(req);
        return AppResp.succeed(null);
    }

    /**
     * 查询宠物领养列表
     * @param req
     * @return
     */
    @PostMapping("/queryList")
    public AppResp<List<PetAdoptVO>> queryPetAdopts(@RequestBody QueryAdoptDTO req) {
        return AppResp.succeed(petAdoptService.queryAdoptList(req));
    }

    /**
     * 查询宠物领养信息
     * @param adoptId
     * @return
     */
    @GetMapping("/getById")
    public AppResp<AdoptAndUserVO> getPetAdoptById(@RequestParam("adoptId") Integer adoptId, @RequestParam(value = "applyId", required = false) Integer applyId, @RequestParam("userId") String userId) {
        return AppResp.succeed(petAdoptService.getPetAdoptById(adoptId, applyId, userId));
    }

    /**
     * 查询宠物领养/寻宠总数
     * @param openId
     * @return
     */
    @GetMapping("/queryCount")
    public AppResp<PetCountVO> queryCount(@RequestParam("openId") String openId) {
        return AppResp.succeed(petAdoptService.queryCount(openId));
    }

    /**
     * 添加申请领养
     * @param req
     * @return
     */
    @PostMapping("/add/applyAdopt")
    public AppResp<Void> addPetAdopt(@RequestBody AddAdoptApplyDTO req) {
        adoptApplyService.add(req);
        return AppResp.succeed(null);
    }

    /**
     * 查询我申请的列表
     * @param userId
     * @return
     */
    @GetMapping("/queryMyApplyList")
    public AppResp<List<MyApplyVO>> queryMyApplyList(@RequestParam("userId") String userId,
                                                     @RequestParam("pageNum") Integer pageNum,
                                                     @RequestParam("pageSize") Integer pageSize) {

        return AppResp.succeed(adoptApplyService.queryMyApplyList(userId, pageNum, pageSize));
    }

    /**
     * 查询我收到的申请列表
     * @param userId
     * @return
     */
    @GetMapping("/queryReceiveApplyList")
    public AppResp<List<ReceiveApplyVO>> queryReceiveApplyList(@RequestParam("userId") String userId,
                                                               @RequestParam("pageNum") Integer pageNum,
                                                               @RequestParam("pageSize") Integer pageSize) {

        return AppResp.succeed(adoptApplyService.queryReceiveApplyList(userId, pageNum, pageSize));
    }

    /**
     * 领养申请详情
     * @param id
     * @return
     */
    @GetMapping("/getApplyById")
    public AppResp<AdoptApplyVO> getApplyById(@RequestParam("id") Integer id) {
        return AppResp.succeed(adoptApplyService.getById(id));
    }

    /**
     * 领养申请审核
     * @param applyId
     * @param status 0-驳回 1-通过
     * @return
     */
    @GetMapping("/audit")
    public AppResp<Boolean> audit(@RequestParam("applyId") Integer applyId, @RequestParam("status") String status) {
        return AppResp.succeed(adoptApplyService.audit(applyId, status));
    }

    /**
     * 查询领养申请未处理消息总数
     * @param userId
     * @return
     */
    @GetMapping("/applyCount")
    public AppResp<Integer> applyCount(@RequestParam("userId") String userId) {
        return AppResp.succeed(adoptApplyService.applyCount(userId));
    }

}
