package com.fjnu.fjnu.controller;

import com.fjnu.fjnu.bean.Article;
import com.fjnu.fjnu.service.IArticleService;
import com.fjnu.fjnu.utils.Result;
import com.fjnu.fjnu.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    IArticleService articleService;


    @RequestMapping("/list")
    public ModelAndView toList(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("article_list");
        return mv;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("article_add");
        return mv;
    }

    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEdit(@PathVariable Integer id){
        ModelAndView mv=new ModelAndView();
        //设置视图
        mv.setViewName("article_edit");
        Article article=articleService.getArticleById(id);
        //设置数据
        mv.addObject("article",article);
        mv.addObject("url","/article/"+article.getHpic());

        return mv;
    }
    @RequestMapping("/edit")
    public Result edit(Article article){
        Result result=null;
        // 先获取原来对象的值
        Article u=articleService.getArticleById(article.getId());
        u.setTitle(article.getTitle());
        u.setHpic(article.getHpic());
        u.setScontent(article.getScontent());
        int data=articleService.update(u);
        result=ResultUtils.success(data);
        if(data>0){
            result.setCode(0);
            result.setMsg("更新成功");
        }
        else{
            result.setMsg("更新失败");
        }
        return result;
    }



    @RequestMapping("/add")
    public Result add(Article article){
        System.out.println("添加数据【"+article+"】");
        //用户数据入库
        articleService.addArticle(article);
        Result result=null;
        result=ResultUtils.success(article);
        result.setCode(0);
        result.setMsg("添加 成功");
        return result;
    }
    @RequestMapping("/getArticles")
    public Result getArticles(){
        Result result=null;
        List<Article> list=articleService.getArticles();
        result=ResultUtils.success(list);
        result.setCode(0);
        result.setMsg("查询成功");
        return result;
    }
    @RequestMapping("/delete/{id}")
    public Result deleteArticle(@PathVariable Integer id){
        Result result=null;
        int data=articleService.delete(id);
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
    @RequestMapping("/uploadHPic")
    public Result uploadHPic(@RequestParam("file") MultipartFile file) throws IOException {
        // 项目路径
        File projectPath = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("projectPath="+projectPath);
        // 绝对路径=项目路径+自定义路径
        File upload = new File(projectPath.getAbsolutePath(), "static/article/");
        if (!upload.exists())
            upload.mkdirs();
        Result result=null;
        if (file.isEmpty()) {
            result=ResultUtils.error(-1,"上传失败");
        }
        else{
            //获取文件名称
            String fileName=file.getOriginalFilename();
            System.out.println("dest="+upload.getAbsolutePath()+"\\"+fileName);
            File dest=new File(upload.getAbsolutePath()+"\\"+fileName);
            //文件IO
            file.transferTo(dest);
            result=ResultUtils.success();
            result.setCode(0);
            result.setMsg("上传成功");
            Map<String,String> map=new HashMap<>();
            map.put("src",fileName);
            result.setData(map);
        }

        return result;
    }
}