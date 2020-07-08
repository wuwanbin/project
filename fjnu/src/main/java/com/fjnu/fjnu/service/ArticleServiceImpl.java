package com.fjnu.fjnu.service;

import com.fjnu.fjnu.bean.Article;
import com.fjnu.fjnu.mapper.ArticleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService{
    //自动装配的注解方式
    /*@Autowired
    IUserDao userDao;*/
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        articleMapper.insert(article);
    }



    @Override
    public List<Article> getArticles() {
        List<Article> list=articleMapper.selectList(null);
        return list;
    }

    @Override
    public Article getArticleById(Integer id) {
        Article article= articleMapper.selectById(id);
        return article;
    }

    @Override
    public int update(Article article) {
        int result=articleMapper.updateById(article);
        return result;
    }

    @Override
    public int delete(Integer id) {
        return articleMapper.deleteById(id);
    }

}
