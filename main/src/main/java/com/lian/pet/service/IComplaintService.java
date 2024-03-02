package com.lian.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lian.pet.domain.dto.QueryCommonDTO;
import com.lian.pet.domain.entity.Complaint;
import com.lian.pet.domain.vo.ComplaintVo;
import java.util.List;

/**
 * <p>
 * 举报信息 服务类
 * </p>
 *
 * @author lian
 * @since 2023-05-21
 */
public interface IComplaintService extends IService<Complaint> {

    void add(ComplaintVo req);

    List<ComplaintVo> queryList(QueryCommonDTO req);

}
