package com.lina.webstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lina.webstore.entity.User;

@SpringBootTest
public class UserServiceTests {

    @Autowired 
    IUserService userService;

    @Test
    public void reg(){

        try {
            User user=new User();
            user.setPassword("test01");
            user.setUsername("test01");
            userService.reg(user);
            
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
        
    }

    @Test
    public void login(){
        User user=userService.login("bbb", "bbb");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userService.updatePasswordByUid(3, "bbb","bbb","bbbb");
        
    }

    @Test
    public void getByUid(){
       User user= userService.getByUid(3);
       System.out.println(user);
        
    }

    @Test
    public void updateInforByUid(){
        User user=new User();
        user.setGender(1);
        user.setPhone("13200356729");
        user.setEmail("test01@qq.com");
       userService.updateInforByUid(130,"lina01",user);
       
        
    }

    
}
