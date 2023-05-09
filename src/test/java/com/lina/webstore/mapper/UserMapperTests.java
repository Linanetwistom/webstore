package com.lina.webstore.mapper;


import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lina.webstore.entity.User;

@SpringBootTest
public class UserMapperTests {

    @Autowired UserMapper userMapper;

    @Test
    public void insert(){
        User user=new User();
        user.setPassword("lina01");
        user.setUsername("lina01");
        Integer row= userMapper.insert(user);
        System.out.println(row);
        
    }

    @Test
    public void findByUsername(){
        System.out.println(userMapper.findByUsername("lina"));
    }

    @Test
    public void findByuid(){
        System.out.println(userMapper.findByuid(128));
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(130,"lina02","lina",new Date());
    }

    @Test
    public void updateInforByUid(){
        User user = new User();
        user.setUid(130);
        user.setGender(1);
        user.setEmail("test@qq.com");
        user.setPhone("1234");
        Integer row=userMapper.updateInforByUid(user);
        System.out.println(row);
    }
    @Test
    public void updataAvatarByUid(){
        userMapper.updataAvatarByUid(130,"lina02/jsos/","lina",new Date());
    }
}
