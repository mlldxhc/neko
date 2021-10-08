package com.study.mapper;

import com.study.pojo.Cat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatMapper {

    List<Cat> queryCat(int count);
    List<String> queryUsername();

    int catCount();

    List<Cat> queryByUsername(@Param("username") String username, @Param("count") int count);

    int userCatCount(String username);

    List<Cat> canAdopt(int count);

    int canAdoptCount();

    Cat queryByID(int id);

    int addCat(Cat cat);

    int updateCat(Cat cat);

    int deleteCat(int id);
}
