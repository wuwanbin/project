package com.fjnu.fjnu.service;

import com.fjnu.fjnu.bean.User;
import com.fjnu.fjnu.dao.IUserDao;
import com.fjnu.fjnu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService{
    //自动装配的注解方式
    /*@Autowired
    IUserDao userDao;*/
    @Autowired

    UserMapper userMapper;
    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        if (user.getUname().equals("zhangsan") && user.getUpassword().equals("123")) {
            return user;
        }
        else{
            return null;
        }

    }

    @Override
    public List<User> getUsers() {
        List<User> list=userMapper.selectList(null);
        return list;
    }

    @Override
    public User getUserById(Integer id) {
       User user= userMapper.selectById(id);
        return user;
    }

    @Override
    public int update(User user) {
        int result=userMapper.updateById(user);
        return result;
    }

    @Override
    public int delete(Integer id) {
        return userMapper.deleteById(id);
    }


}
