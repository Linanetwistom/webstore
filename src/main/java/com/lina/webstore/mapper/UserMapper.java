package com.lina.webstore.mapper;

import java.util.Date;


import com.lina.webstore.entity.User;

public interface UserMapper {
    
    Integer insert(User user);

    User findByUsername(String username);

    User findByuid(Integer uid);

    Integer updatePasswordByUid(Integer uid,String password,String modifiedUser,Date modifiedTime);

    Integer updateInforByUid(User user);

    Integer updataAvatarByUid(Integer uid,String avatar,String modifiedUser,Date modifiedTime);
}
