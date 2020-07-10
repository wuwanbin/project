package com.fjnu.fjnu.controller;

import com.fjnu.fjnu.bean.Article;
import com.fjnu.fjnu.bean.Classify;
import com.fjnu.fjnu.service.IArticleService;
import com.fjnu.fjnu.service.IClassifyService;
import com.fjnu.fjnu.utils.Result;
import com.fjnu.fjnu.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController

public class PortalController<JsonResult> {
    @Autowired
    IArticleService articleService;
    @Autowired
    IClassifyService classifyService;



    @RequestMapping("/user/portal_index")
    public ModelAndView toIndex(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("portal_index");
        //获取分类列表
        List<Classify> classifies = classifyService.getClassifies();
        mv.addObject("classifies",classifies);

        //获取文章列表
        List<Article> articles=articleService.getArticles();
        mv.addObject("articles",articles);
        return  mv;

    }
    //点击分类管理
    @RequestMapping("/user/classify_manager")
    public ModelAndView toManager(){
        System.out.println("进入分类管理......");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("classify_manager");
        return mv;
    }
    @RequestMapping("/user/getClassifies")
    public Result getClassifies(){
        Result result=null;
        List<Classify> list=classifyService.getClassifies();
        result= ResultUtils.success(list);
        result.setCode(0);
        result.setMsg("获取数据成功");
        return result;
    }

    @RequestMapping("/user/to_classify_add")
    public ModelAndView toAdd(){
        System.out.println("添加用户......");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("classify_add");
        return mv;
    }
    @RequestMapping("/user/classify_add")
    public Result add(Classify classify){
        System.out.println("添加分类【"+classify+"】");
        //用户数据入库
        classifyService.addClassify(classify);
        Result result=null;
        result=ResultUtils.success(classify);
        result.setCode(0);
        result.setMsg("添加成功");
        return result;
    }
    @RequestMapping("/user/classify_delete/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = null;
        int data = classifyService.delete(id);
        result=ResultUtils.success(data);
        if (data>0){
            result.setCode(0);
            result.setMsg("删除成功");
        }
        else{
            result.setMsg("删除失败");
        }
        return result;

    }

    @RequestMapping("/user/to_classify_edit/{id}")
    public ModelAndView to_Classify_edit(@PathVariable Integer id){
        System.out.println("编辑分类......");
        ModelAndView mv = new ModelAndView();
        //设置视图
        mv.setViewName("classify_edit");
        Classify c = classifyService.getClassifyById(id);
        //设置数据
        mv.addObject("classify",c);
        return mv;
    }

    @RequestMapping("/user/classify_edit")
    public Result classify_edit(Classify classify){
        Result result =null;
        //先获取原来对象的值
        Classify c = classifyService.getClassifyById(classify.getId());
        c.setCname(classify.getCname());
        c.setPid(classify.getPid());
        int r = classifyService.update(c);
        result = ResultUtils.success(r);
        if(r > 0){
            result.setCode(0);
            result.setMsg("更新成功");
        }
        else{
            result.setMsg("更新失败");
        }
        return result;
    }
    @RequestMapping("/portal/article/{id}")
    public ModelAndView toIndex(@PathVariable Integer id){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("portal_article");
        Article article=articleService.getArticleById(id);
        mv.addObject("article",article);
        return  mv;

    }





}
