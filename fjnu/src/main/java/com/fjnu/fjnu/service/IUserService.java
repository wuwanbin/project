package com.fjnu.fjnu.service;

import com.fjnu.fjnu.bean.User;

import java.util.List;

public interface IUserService {
    public void addUser(User user);
    public User login(User user);
    public List<User> getUsers();
    public  User getUserById(Integer id);
    public  int update(User user);
    public int delete(Integer id);
}
