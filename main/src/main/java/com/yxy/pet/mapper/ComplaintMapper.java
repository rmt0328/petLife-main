package com.yxy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxy.pet.domain.entity.Complaint;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 举报信息 Mapper 接口
 * </p>
 *
 * @author yxy
 * @since 2023-05-21
 */
@Mapper
public interface ComplaintMapper extends BaseMapper<Complaint> {

}
