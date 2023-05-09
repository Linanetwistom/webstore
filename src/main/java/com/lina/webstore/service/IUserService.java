package com.lina.webstore.service;

import com.lina.webstore.entity.User;

public interface IUserService {
    void reg(User user);

    User login(String username,String password);

    void updatePasswordByUid(Integer uid,String username,String oldpassword,String newpassword);

    User getByUid(Integer uid);

    void updateInforByUid(Integer uid,String username,User user);

    void changeAvatar(Integer uid,String username,String avatar);
}
