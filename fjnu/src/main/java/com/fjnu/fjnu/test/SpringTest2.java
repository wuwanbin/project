package com.fjnu.fjnu.test;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fjnu.fjnu.bean.User;
import com.fjnu.fjnu.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest2 {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test01(){
        User user=userMapper.selectById(1);
        System.out.println(user);
    }
    @Test
    public void test02(){
        User user=userMapper.selectById(2);
        System.out.println(user);
    }
    @Test
    public void test03(){
        //添加用户
        User user=new User(null,"Jennie","123");
        userMapper.insert(user);
    }
    @Test
    public void test04(){
        //查询所有用户
        List<User> list= userMapper.selectList(null);
        for (User u:list
        ) {
            System.out.println(u);
        }
    }

    @Test
    public void test05(){
        //带条件查询
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("uname","Jennie");
        List<User> list= userMapper.selectList(wrapper);
        for (User u:list
        ) {
            System.out.println(u);
        }
    }
    @Test
    public void test06(){
        User user=userMapper.getUserById(1);
        System.out.println(user);
    }

}

