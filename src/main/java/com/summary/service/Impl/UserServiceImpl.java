package com.summary.service.Impl;

import com.summary.dao.UserDao;
import com.summary.entity.User;
import com.summary.service.UserService;

public class UserServiceImpl implements UserService {

    //todo 异常处理

    @Override
    public User userLogin(User user) {
        UserDao userDao = new UserDao();
        return userDao.userLogin(user);

    }
    @Override
    public int userRegister(User user) {
        UserDao userDao = new UserDao();
        return userDao.insertUser(user);
    }

    @Override
    public int userUpdate(User user) {
        UserDao userDao = new UserDao();
        return userDao.updateUser(user);
    }

    public int deleteUser(User user){
        UserDao userDao = new UserDao();
        return userDao.deleteUser(user);
    }

    @Override
    public int isUser(User user) {
        int flag = 0;
        UserDao userDao = new UserDao();
        flag = userDao.isUser(user);
        return flag;
    }


}
