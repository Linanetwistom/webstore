package com.lina.webstore.service.impl;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.lina.webstore.entity.User;
import com.lina.webstore.mapper.UserMapper;
import com.lina.webstore.service.IUserService;
import com.lina.webstore.service.ex.InsertException;
import com.lina.webstore.service.ex.PasswordNotMatchException;
import com.lina.webstore.service.ex.UpdateExceptrion;
import com.lina.webstore.service.ex.UserNotFoundException;
import com.lina.webstore.service.ex.UsernameDuplicatedServiceExceptionn;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public void reg(User user) {
        String username=user.getUsername();
        User result = userMapper.findByUsername(username);
        if(result!=null){
            throw new UsernameDuplicatedServiceExceptionn("用户名被占用");
        }

        String oldPassword=user.getPassword();
        String salt=UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5Password=getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);


        user.setIsDelete(0);
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        Date date= new Date();

        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer row=userMapper.insert(user);
        if(row!=1){
            throw new InsertException("用户注册时异常");
        }

    }

    private String getMD5Password(String password,String salt){
        for(int i=0;i<3;i++){
          password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
        
    }

    @Override
    public User login(String username, String password) {
        User result=userMapper.findByUsername(username);
        if(result==null){
            throw new UserNotFoundException("用户名未找到");
        }
        String oldPassword=result.getPassword();
        String salt=result.getSalt();
        String newPassword=getMD5Password(password, salt);
        if(!oldPassword.equals(newPassword)){
            throw new  PasswordNotMatchException("用户名和密码不匹配");
        }
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户已被删除");
        }

        User user=new User();
        user.setUid(result.getUid());
        user.setAvatar(result.getAvatar());
        user.setUsername(result.getUsername());
        return  user;
    }


    @Override
    public void updatePasswordByUid(Integer uid, String username, String oldPassword, String newPassword) {
        // TODO Auto-generated method stub
        User result=userMapper.findByuid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        String oldMd5psw=getMD5Password(oldPassword, result.getSalt());
        if(!oldMd5psw.equals(result.getPassword())){
            throw new PasswordNotMatchException("密码错误");
        }

        String newMd5psw=getMD5Password(newPassword, result.getSalt());
        Integer row=userMapper.updatePasswordByUid(uid, newMd5psw, result.getUsername(), new Date());
        if(row!=1){
            throw new UpdateExceptrion("用户密码更新异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {

        User result=userMapper.findByuid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }

        User user= new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setGender(result.getGender());
        user.setEmail(result.getEmail());

        return user;
    }

    @Override
    public void updateInforByUid(Integer uid, String username, User user) {
        User result=userMapper.findByuid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }

        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer row=userMapper.updateInforByUid(user);
        if(row!=1){
            throw new UpdateExceptrion("数据更新异常");
        }

    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User result=userMapper.findByuid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        Integer row=userMapper.updataAvatarByUid(uid, avatar, username, new Date());
        if(row!=1){
            throw new UpdateExceptrion("更新用户头像异常");
        }
    }
    
}