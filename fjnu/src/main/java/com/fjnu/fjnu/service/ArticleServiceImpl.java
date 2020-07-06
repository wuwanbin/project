package com.fjnu.fjnu.service;

import com.fjnu.fjnu.bean.Article;
import com.fjnu.fjnu.bean.User;
import com.fjnu.fjnu.mapper.ArticleMapper;
import com.fjnu.fjnu.mapper.UserMapper;
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
    public List<Article> getArticles() {
        return articleMapper.selectList(null);
    }
}
