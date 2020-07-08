package com.fjnu.fjnu.service;

import com.fjnu.fjnu.bean.Article;


import java.util.List;

public interface IArticleService {

    void addArticle(Article article);

    public List<Article> getArticles();

    Article getArticleById(Integer id);

    int update(Article article);

    int delete(Integer id);


}
