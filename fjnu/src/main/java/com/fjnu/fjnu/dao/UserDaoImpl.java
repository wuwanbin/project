package com.fjnu.fjnu.dao;

import com.fjnu.fjnu.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImpl implements IUserDao{


    @Override
    public void save(User user) {
        System.out.println("【添加用户】"+user.getUname());
    }

    @Override
    public User getUserById(Integer id) {
        System.out.println("【获取用户】"+id);
        return null;
    }

    @Override
    public List<User> getUser() {
        return null;
    }
}
