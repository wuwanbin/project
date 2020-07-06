package com.fjnu.fjnu.test;

import com.fjnu.fjnu.bean.User;
import com.fjnu.fjnu.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {
    @Autowired
    IUserService userService;
    @Test
    public void test1(){
    userService.addUser(new User(1,"zhangsan","333"));
    }
}
