package com.fjnu.fjnu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjnu.fjnu.bean.Article;
import com.fjnu.fjnu.bean.Classify;
import com.fjnu.fjnu.bean.User;

import java.util.List;

public interface ClassifyMapper extends BaseMapper<Classify> {
    public Classify getClassifyById(Integer id);
}

