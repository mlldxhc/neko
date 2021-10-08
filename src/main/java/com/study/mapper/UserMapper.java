package com.study.mapper;

import com.study.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Mapper这个注解表示了这是一个mybatis的mapper类，或者在springboot主程序类上加入包扫描@MapperScan("com.study.mapper")
@Repository
public interface UserMapper {

    ArrayList<User> queryUser(int count);
    ArrayList<User> queryUserAll();

    User queryUserByName(String name);

    int userCount();

    User queryByID(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
