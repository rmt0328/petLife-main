package com.lian.pet.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.lian.pet.common.basic.enums.FinishStatusEnum;
import com.lian.pet.common.basic.enums.IsPassEnum;
import com.lian.pet.common.basic.exception.AppException;
import com.lian.pet.common.basic.utils.DateUtil;
import com.lian.pet.domain.dto.AddAdoptApplyDTO;
import com.lian.pet.domain.entity.AdoptApply;
import com.lian.pet.domain.entity.PetAdopt;
import com.lian.pet.domain.vo.AdoptApplyVO;
import com.lian.pet.domain.vo.MyApplyVO;
import com.lian.pet.domain.vo.ReceiveApplyVO;
import com.lian.pet.mapper.AdoptApplyMapper;
import com.lian.pet.mapper.PetAdoptMapper;
import com.lian.pet.service.AdoptApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: Lian
 * @Time: 2022/4/30 12:24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AdoptApplyServiceImpl implements AdoptApplyService {
    private final AdoptApplyMapper adoptApplyMapper;
    private final PetAdoptMapper petAdoptMapper;

    @Override
    public void add(AddAdoptApplyDTO dto) {
        Integer count = adoptApplyMapper.selectCount(Wrappers.<AdoptApply>lambdaQuery()
                .eq(AdoptApply::getAdoptId, dto.getAdoptId())
                .eq(AdoptApply::getUserId, dto.getUserId())
                .ne(AdoptApply::getIsPassed, IsPassEnum.NO_PASS.code()));

        if (count > 0) {
            throw new AppException("重复申请");
        }
        AdoptApply adoptApply = new AdoptApply();
        BeanUtils.copyProperties(dto, adoptApply);
        adoptApplyMapper.insert(adoptApply);
    }

    @Override
    public AdoptApplyVO getById(Integer id) {
        AdoptApplyVO adoptApplyVO = AdoptApplyVO.fromAdoptApply(adoptApplyMapper.selectById(id));
        PetAdopt petAdopt = petAdoptMapper.selectById(adoptApplyVO.getAdoptId());
        adoptApplyVO.setAdoptTitle(petAdopt.getTitle());
        return adoptApplyVO;
    }

    @Override
    public List<MyApplyVO> queryMyApplyList(String userId, Integer pageNum, Integer pageSize) {
        IPage<AdoptApply> page = new Page<>(pageNum, pageSize);
        IPage<AdoptApply> adoptApplyIPage = adoptApplyMapper.selectPage(page, Wrappers.<AdoptApply>lambdaQuery()
                .eq(AdoptApply::getUserId, userId)
                .orderByDesc(AdoptApply::getCreateTime));

        if (adoptApplyIPage.getRecords().isEmpty()) {
            return Lists.newArrayList();
        }
        List<MyApplyVO> applyVOS = new ArrayList<>();
        adoptApplyIPage.getRecords().forEach(adoptApply -> {
            PetAdopt petAdopt = petAdoptMapper.selectById(adoptApply.getAdoptId());
            applyVOS.add(MyApplyVO.builder()
                    .applyId(adoptApply.getId())
                    .applyName(petAdopt.getTitle())
                    .adoptId(petAdopt.getId())
                    .applyTime(DateUtil.dateToString(adoptApply.getCreateTime()))
                    .status(adoptApply.getIsPassed())
                    .build());
        });
        return applyVOS;
    }

    @Override
    public List<ReceiveApplyVO> queryReceiveApplyList(String userId, Integer pageNum, Integer pageSize) {
        IPage<AdoptApply> page = new Page<>(pageNum, pageSize);
        IPage<AdoptApply> adoptApplyIPage = adoptApplyMapper.selectPage(page, Wrappers.<AdoptApply>lambdaQuery()
                .eq(AdoptApply::getAdoptUserId, userId)
                .orderByDesc(AdoptApply::getCreateTime));

        if (adoptApplyIPage.getRecords().isEmpty()) {
            return Lists.newArrayList();
        }
        List<ReceiveApplyVO> receiveApplyVOS = new ArrayList<>();
        adoptApplyIPage.getRecords().forEach(adoptApply -> {
            PetAdopt petAdopt = petAdoptMapper.selectById(adoptApply.getAdoptId());
            receiveApplyVOS.add(ReceiveApplyVO.builder()
                    .id(adoptApply.getId())
                    .adoptName(petAdopt.getTitle())
                    .userName(adoptApply.getUserName())
                    .applyTime(DateUtil.dateToString(adoptApply.getCreateTime()))
                    .isPassed(adoptApply.getIsPassed())
                    .build());
        });
        return receiveApplyVOS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean audit(Integer applyId, String status) {
        AdoptApply adoptApply = adoptApplyMapper.selectById(applyId);
        if ("1".equals(status)) {
            adoptApply.setIsPassed(IsPassEnum.PASSED.code());
            PetAdopt petAdopt = petAdoptMapper.selectById(adoptApply.getAdoptId());
            petAdopt.setIsFinish(FinishStatusEnum.FINISHED.code());
            petAdoptMapper.updateById(petAdopt);

            // 被领养时 更新其他审核中的记录为未通过
            List<AdoptApply> adoptApplies = adoptApplyMapper.selectList(Wrappers.<AdoptApply>lambdaQuery()
                    .eq(AdoptApply::getAdoptId, adoptApply.getAdoptId())
                    .ne(AdoptApply::getId, adoptApply.getId()));
            if (!CollectionUtils.isEmpty(adoptApplies)) {
                adoptApplies.forEach(e -> {
                    e.setIsPassed(IsPassEnum.NO_PASS.code());
                    adoptApplyMapper.updateById(e);
                });
            }
        } else adoptApply.setIsPassed(IsPassEnum.NO_PASS.code());

        adoptApplyMapper.updateById(adoptApply);
        return true;
    }

    @Override
    public Integer applyCount(String userId) {
        return adoptApplyMapper.selectCount(Wrappers.<AdoptApply>lambdaQuery()
                .eq(AdoptApply::getAdoptUserId, userId)
                .eq(AdoptApply::getIsPassed, IsPassEnum.IS_AUDIT.code()));
    }

}
