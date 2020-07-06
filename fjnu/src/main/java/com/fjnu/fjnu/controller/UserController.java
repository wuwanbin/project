package com.fjnu.fjnu.controller;

import com.fjnu.fjnu.bean.User;
import com.fjnu.fjnu.service.IUserService;
import com.fjnu.fjnu.utils.Result;
import com.fjnu.fjnu.utils.ResultEnum;
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
@RequestMapping("/user")
public class UserController {
        @Autowired
        IUserService userService;
        /* @RequestMapping 映射路径*/
        @RequestMapping("/doLogin")
        public Result login(String uname, String upassword){
            Result result=null;
            System.out.println("执行登陆.....");
            System.out.println("【用户名】"+uname+",【密码】"+upassword);
            User user=new User();
            user.setUname(uname);
            user.setUpassword(upassword);
            User loginUser=userService.login(user);
            if(loginUser!=null){
                result=ResultUtils.success(user);
            }
            else{
                result=ResultUtils.success(user);
                result.setCode(ResultEnum.LOGIN_FAILS.getCode());
                result.setMsg(ResultEnum.LOGIN_FAILS.getMsg());
            }
            return result;

        }

        /*
    * 进入登录页面
    * */
    @RequestMapping("/toLogin")
    public ModelAndView toLogin(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/list")
    public ModelAndView toList(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("user_list");
        return mv;
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("user_add");
        return mv;
    }

    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEdit(@PathVariable Integer id){
        ModelAndView mv=new ModelAndView();
        //设置视图
        mv.setViewName("user_edit");
        User user=userService.getUserById(id);
        //设置数据
        mv.addObject("user",user);
        mv.addObject("url","/user/"+user.getHeadpic());

        return mv;
    }
    @RequestMapping("/edit")
    public Result edit(User user){
        Result result=null;
        // 先获取原来对象的值
        User u=userService.getUserById(user.getID());
        u.setUname(user.getUname());
        u.setHeadpic(user.getHeadpic());
        int data=userService.update(u);
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
    public Result add(User user){
        System.out.println("添加数据【"+user+"】");
        //用户数据入库
        userService.addUser(user);
        Result result=null;
        result=ResultUtils.success(user);
        result.setCode(0);
        result.setMsg("添加 成功");
        return result;
    }
    @RequestMapping("/getUsers")
    public Result getUsers(){
        Result result=null;
        List<User> list=userService.getUsers();
        result=ResultUtils.success(list);
        result.setCode(0);
        result.setMsg("查询成功");
        return result;
    }
    @RequestMapping("/delete/{id}")
    public Result deleteUser(@PathVariable Integer id){
        Result result=null;
        int data=userService.delete(id);
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

    @RequestMapping("/uploadHeadPic")
    public Result uploadHeadPic(@RequestParam("file") MultipartFile file) throws IOException {
        // 项目路径
        File projectPath = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("projectPath="+projectPath);
        // 绝对路径=项目路径+自定义路径
        File upload = new File(projectPath.getAbsolutePath(), "static/user/");
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
