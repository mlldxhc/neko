package com.study.service.cat;

import com.study.mapper.CatMapper;
import com.study.pojo.Cat;
import com.study.service.cat.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {

    private CatMapper catMapper;

    @Autowired
    public void setCatMapper(CatMapper catMapper) {
        this.catMapper = catMapper;
    }

    @Override
    public List<String> queryUsername(){
        return catMapper.queryUsername();
    };
    @Override
    public List<Cat> queryCat(int count) {
        return catMapper.queryCat(count);
    }

    @Override
    public int catCount() {
        return catMapper.catCount();
    }

    @Override
    public List<Cat> queryByUsername(String username, int count) {
        return catMapper.queryByUsername(username, count);
    }

    @Override
    public int userCatCount(String username) {
        return catMapper.userCatCount(username);
    }

    @Override
    public List<Cat> canAdopt(int count) {
        return catMapper.canAdopt(count);
    }

    @Override
    public int canAdoptCount() {
        return catMapper.canAdoptCount();
    }

    @Override
    public Cat queryByID(int id) {
        return catMapper.queryByID(id);
    }

    @Override
    public int addCat(Cat Cat) {
        return catMapper.addCat(Cat);
    }

    @Override
    public int updateCat(Cat Cat) {
        return catMapper.updateCat(Cat);
    }

    @Override
    public int deleteCat(int id) {
        return catMapper.deleteCat(id);
    }
}
