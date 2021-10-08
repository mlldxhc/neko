package com.study.controller;

import com.study.mapper.CatMapper;
import com.study.mapper.UserMapper;
import com.study.pojo.Cat;
import com.study.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserInfoController {

    private CatMapper catMapper;

    private UserMapper userMapper;

    @Autowired
    public void setCatMapper(CatMapper catMapper) {
        this.catMapper = catMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    private int totalPage = -1;

    private String getUsername(HttpSession session) {
        return (String) session.getAttribute("loginUser");
    }

    @RequestMapping("/userInfo")
    public String index(Model model, Integer page, HttpSession session) {
        User newUser = userMapper.queryUserByName((String) session.getAttribute("loginUser"));
        model.addAttribute("perms",newUser.getPerms());
        if (page == null) {
            page = 1;
        }
        if (totalPage == -1) {
            totalPage = (catMapper.userCatCount(getUsername(session)) - 1) / 5 + 1;
        }
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        return "user_info";
    }

    @RequestMapping("/adoptAjax")
    @ResponseBody
    public List<Cat> adoptAjax(int page, HttpSession session) {
        int count = (page - 1) * 5;
        return catMapper.queryByUsername(getUsername(session), count);
    }

    @RequestMapping("/discCat/?/{id}")
    public String discCat( @PathVariable("id") int id){
        Cat cat = catMapper.queryByID(id);
        cat.setUsername(null);
        cat.setAdoptDate(null);
        catMapper.updateCat(cat);
        return "redirect:/userInfo";
    }

}
