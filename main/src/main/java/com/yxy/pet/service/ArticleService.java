package com.yxy.pet.service;

import com.yxy.pet.domain.dto.QueryArticleDTO;
import com.yxy.pet.domain.vo.ArticleVO;

import java.util.List;

/**
 * @Desc: Article Service
 * @Author: yxy
 * @Time: 2022/2/3 16:18
 */
public interface ArticleService {

    /**
     * 查询文章列表
     * @param req
     * @return
     */
    List<ArticleVO> queryArticles(QueryArticleDTO req);
}
