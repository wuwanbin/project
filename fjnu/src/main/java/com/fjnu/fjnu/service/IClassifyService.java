package com.fjnu.fjnu.service;

import com.fjnu.fjnu.bean.Article;
import com.fjnu.fjnu.bean.Classify;
import com.fjnu.fjnu.bean.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IClassifyService {

    public List<Classify> getClassifies();
    public Classify getClassifyById(Integer id);
    public void addClassify(Classify classify);
    public int delete(Integer id);
    public int update(Classify classify);

}
