package com.join.dao;

import com.join.entity.User;

import java.util.List;

/**
 * dao等价于以后的mapper
 */
public interface UserDao {

    List<User> getUserList();


}
