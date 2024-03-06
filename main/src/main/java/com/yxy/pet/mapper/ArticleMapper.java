package com.yxy.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxy.pet.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yxy
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}