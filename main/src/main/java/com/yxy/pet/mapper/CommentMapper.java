package com.yxy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxy.pet.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yxy
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}