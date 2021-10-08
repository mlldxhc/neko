package com.study.service.user;

import com.study.pojo.Cat;
import com.study.pojo.User;

import java.util.List;


public interface UserService {

    List<User> queryUser(int count);
    List<User> queryUserAll();
    User queryUserByName(String name);

    int userCount();

    User queryByID(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

}
