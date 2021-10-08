package com.study.service.cat;

import com.study.pojo.Cat;

import java.util.List;



public interface CatService {

    List<Cat> queryCat(int count);
    List<String> queryUsername();
    int catCount();

    List<Cat> queryByUsername(String username, int count);

    int userCatCount(String username);

    List<Cat> canAdopt(int count);

    int canAdoptCount();

    Cat queryByID(int id);

    int addCat(Cat cat);

    int updateCat(Cat cat);

    int deleteCat(int id);

}
