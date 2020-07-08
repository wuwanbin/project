package com.fjnu.fjnu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjnu.fjnu.bean.Article;


public interface ArticleMapper extends BaseMapper<Article> {
    //自定义查询
    public Article getArticleById(Integer id);

}

