package com.study.service.user;

import com.study.mapper.UserMapper;
import com.study.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ArrayList<User> queryUser(int count) {
        return userMapper.queryUser(count);
    }
    @Override
    public ArrayList<User> queryUserAll() {
        return userMapper.queryUserAll();
    }


    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    public int userCount() {
        return userMapper.userCount();
    }

    @Override
    public User queryByID(int id) {
        return userMapper.queryByID(id);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }


}
