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
public class IndexController {

    private CatMapper catMapper;

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setCatMapper(CatMapper catMapper) {
        this.catMapper = catMapper;
    }

    private int totalPage = -1;

    private int totalPage_all = -1;

    @RequestMapping({"/", "/index.html"})
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model, Integer page, Integer page_all,HttpSession session) {
        User newUser = userMapper.queryUserByName((String) session.getAttribute("loginUser"));
        model.addAttribute("perms",newUser.getPerms());
        if (page == null) {
            page = 1;
        }
        if (page_all == null) {
            page_all = 1;
        }
        if (totalPage == -1) {
            totalPage = (catMapper.canAdoptCount() - 1) / 5 + 1;
        }
        if (totalPage_all == -1) {
            totalPage_all = (catMapper.catCount() - 1) / 5 + 1;
        }
        model.addAttribute("page", page);
        model.addAttribute("page_all", page_all);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalPage_all", totalPage_all);
        return "index";
    }

    @RequestMapping("/adoptCat/?/{id}")
    public String adoptCat(@PathVariable("id") int id,
                           HttpSession session){
        String username = (String) session.getAttribute("loginUser");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        Cat cat = catMapper.queryByID(id);
        cat.setUsername(username);
        cat.setAdoptDate(strDate);
        catMapper.updateCat(cat);
        return "redirect:/userInfo";
    }

    @RequestMapping("/canAdoptAjax")
    @ResponseBody
    public List<Cat> canAdoptAjax(int page) {
        int count = (page - 1) * 5;
        return catMapper.canAdopt(count);
    }
    @RequestMapping("/catImg/{id}")
    public String catImg(@PathVariable("id") String id,Model model){
        model.addAttribute("catID",id);
        return "catImg";
    }

}
