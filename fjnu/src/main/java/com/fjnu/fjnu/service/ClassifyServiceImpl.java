package com.fjnu.fjnu.service;

import com.fjnu.fjnu.bean.Classify;
import com.fjnu.fjnu.mapper.ClassifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ClassifyServiceImpl implements IClassifyService{
    //自动装配的注解方式
    /*@Autowired
    IUserDao userDao;*/
    @Autowired

    ClassifyMapper classifyMapper;


    @Override
    public List<Classify> getClassifies() {
        return classifyMapper.selectList(null);
    }

    @Override
    public Classify getClassifyById(Integer id) {
        Classify c = classifyMapper.selectById(id);
        return c;
    }

    @Override
    public void addClassify(Classify classify) {
        classifyMapper.insert(classify);
    }

    @Override
    public int delete(Integer id) {
        return classifyMapper.deleteById(id);
    }

    @Override
    public int update(Classify classify) {
        int result = classifyMapper.updateById(classify);
        return result;
    }

}
