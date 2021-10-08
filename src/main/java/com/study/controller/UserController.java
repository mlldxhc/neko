package com.study.controller;

import com.study.mapper.CatMapper;
import com.study.mapper.UserMapper;
import com.study.pojo.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;


@Controller
public class UserController {

    private UserMapper userMapper;
    private CatMapper catMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setCatMapper(CatMapper catMapper) {
        this.catMapper = catMapper;
    }

    private int totalPage = -1;

    @RequestMapping("/users")   // 所有用户
    public String list(Model model, Integer page) {
        ArrayList<User> users = userMapper.queryUserAll();
        List<String> usernames = catMapper.queryUsername();
        Map<String,Integer> map = new HashMap<>();
        for (String username : usernames) {
            Integer count = map.get(username);
            map.put(username,(count == null) ? 1:count+1);
        }
        for (String s : map.keySet()) {
            for (User user : users) {
                if(user.getUsername().equals(s)){
                    user.setcats(map.get(s));
                    userMapper.updateUser(user);
                }
            }
        }


        if (page == null) {
            page = 1;
        }
        if (totalPage == -1) {
            totalPage = (userMapper.userCount() - 1) / 5 + 1;
        }
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        return "basic_table";
    }

    @RequestMapping("/userAjax")
    @ResponseBody
    public List<User> userAjax(int page) {
        int count = (page - 1) * 5;
        return userMapper.queryUser(count);
    }

    @RequestMapping("/nameAjax")
    @ResponseBody
    public String nameAjax(String username) {
        User user = userMapper.queryUserByName(username);
        if (user == null) {
            return "1";
        } else {
            return "0";
        }
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "add_table";
    }

    @RequiresPermissions("admin")
    @RequestMapping("/addUser")
    public String addUser(User user, Model model) {
        if ("1".equals(user.getPerms())) {
            user.setPerms("admin");
        } else {
            user.setPerms("user");
        }
        userMapper.addUser(user);
        int totalPage = (userMapper.userCount() - 1) / 5 + 1;
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", totalPage);
        return "basic_table";
    }

    @RequiresPermissions("admin")
    @RequestMapping("/delete/{page}/{id}")
    public String delete(@PathVariable("page") Integer page, @PathVariable("id") Integer id, Model model) {
        userMapper.deleteUser(id);
        int count = userMapper.userCount();
        totalPage = (count - 1) / 5 + 1;
        if (count % 5 == 0 && count != 0) {
            page--;
        }
        System.out.println(page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", page);
        return "basic_table";
    }

    @RequiresPermissions("admin")
    @RequestMapping("/toUpdate/{page}/{id}")
    public String toUpdate(@PathVariable("page") Integer page, @PathVariable("id") Integer id, Model model) {
        User user = userMapper.queryByID(id);
        model.addAttribute("user", user);
        model.addAttribute("page", page);
        return "update_table";
    }

    @RequiresPermissions("admin")
    @RequestMapping("/updateUser")
    public String updateEmployee(User user, Integer page, Model model) {
        if ("1".equals(user.getPerms())) {
            user.setPerms("admin");
        } else {
            user.setPerms("user");
        }
        userMapper.updateUser(user);
        if (totalPage == -1) {
            totalPage = (userMapper.userCount() - 1) / 5 + 1;
        }
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", page);
        return "basic_table";
    }

}
