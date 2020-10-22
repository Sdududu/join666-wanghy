package com.summary.service;

import com.summary.dao.UserDao;
import com.summary.entity.User;

public interface UserService {

//    用户登录
    public User userLogin(User user);
//用户注册   
    public int userRegister(User user);
 // 用户信息更改
    public int userUpdate(User user);
    //删除一个用户
    public int deleteUser(User user);
    //判断是否有该用户
    public int isUser(User user);

    
    
}
