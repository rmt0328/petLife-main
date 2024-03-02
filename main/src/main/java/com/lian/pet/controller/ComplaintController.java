package com.lian.pet.controller;

import com.lian.pet.common.basic.exception.AppErrorEnum;
import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.domain.dto.QueryCommonDTO;
import com.lian.pet.domain.entity.Complaint;
import com.lian.pet.domain.vo.ComplaintVo;
import com.lian.pet.service.IComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 举报信息 前端控制器
 * </p>
 *
 * @author lian
 * @since 2023-05-21
 */
@Slf4j
@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private IComplaintService iComplaintService;

    @PostMapping("/add")
    public AppResp<Void> addPetAdopt(@RequestBody ComplaintVo req) {
        if (ObjectUtils.isEmpty(req.getUserId())) {
            log.error("终止流程[userId为空]");
            return AppResp.failed(AppErrorEnum.BAD_REQUEST.getCode(), AppErrorEnum.BAD_REQUEST.getDefMsg());
        }
        iComplaintService.add(req);
        return AppResp.succeed(null);
    }

    @GetMapping("/queryList")
    public AppResp<List<ComplaintVo>> queryList(QueryCommonDTO req) {
        return AppResp.succeed(iComplaintService.queryList(req));
    }

}
