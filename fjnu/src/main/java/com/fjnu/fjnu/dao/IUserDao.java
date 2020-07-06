package com.fjnu.fjnu.dao;

import com.fjnu.fjnu.bean.User;

import java.util.List;

public interface IUserDao {
    public void save(User user);
    public  User getUserById(Integer id);
    public List<User> getUser();
}
