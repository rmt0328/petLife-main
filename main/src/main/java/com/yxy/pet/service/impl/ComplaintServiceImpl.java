package com.yxy.pet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.yxy.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.yxy.pet.domain.dto.QueryCommonDTO;
import com.yxy.pet.domain.entity.Complaint;
import com.yxy.pet.domain.vo.ComplaintVo;
import com.yxy.pet.mapper.ComplaintMapper;
import com.yxy.pet.service.IComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 举报信息 服务实现类
 * </p>
 *
 * @author yxy
 * @since 2023-05-21
 */
@RequiredArgsConstructor
@Service
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements IComplaintService {
    private final ComplaintMapper complaintMapper;
    private final AliYunOssProperties ossProperties;

    @Override
    public void add(ComplaintVo req) {
        Complaint complaint = BeanUtil.copyProperties(req, Complaint.class);
        complaint.setImages(JSON.toJSONString(req.getImages()));
        complaintMapper.insert(complaint);
    }

    @Override
    public List<ComplaintVo> queryList(QueryCommonDTO req) {
        IPage<Complaint> page = new Page<>(req.getPageNum(), req.getPageSize());
        IPage<Complaint> iPage = complaintMapper.selectPage(page, Wrappers.<Complaint>lambdaQuery()
                .eq(StringUtils.isNotBlank(req.getUserId()), Complaint::getUserId, req.getUserId())
                .eq(StringUtils.isNotBlank(req.getStatus()), Complaint::getStatus, req.getStatus())
                .orderByDesc(Complaint::getCreateTime));

        if (CollUtil.isEmpty(iPage.getRecords())) {
            return Lists.newArrayList();
        }

        List<ComplaintVo> complaintVos = BeanUtil.copyToList(iPage.getRecords(), ComplaintVo.class);
        complaintVos.forEach(e -> {
            e.setSexName("1".equals(e.getSex()) ? "男孩" : ("2".equals(e.getSex()) ? "女孩" : "未知"));
            e.setTypeName("1".equals(e.getType()) ? "领养人" : "送养人");
        });
        return complaintVos;
    }
}
