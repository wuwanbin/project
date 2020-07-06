package com.fjnu.fjnu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjnu.fjnu.bean.User;

public interface UserMapper extends BaseMapper<User> {
    //自定义查询
    public User getUserById(Integer id);

}

