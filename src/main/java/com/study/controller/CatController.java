package com.study.controller;
import com.study.mapper.CatMapper;
import com.study.mapper.UserMapper;
import com.study.pojo.Cat;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CatController {

    private CatMapper catMapper;

    @Autowired
    public void setCatMapper(CatMapper catMapper) {
        this.catMapper = catMapper;
    }

    private int totalPage = -1;

    @RequestMapping("/cats")
    public String list(Model model, Integer page) {
        if (page == null) {
            page = 1;
        }
        if (totalPage == -1) {
            totalPage = (catMapper.catCount() - 1) / 5 + 1;
        }
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        return "cat_table";
    }

    @RequestMapping("/catAjax")
    @ResponseBody
    public List<Cat> catAjax(int page) {
        int count = (page - 1) * 5;
        return catMapper.queryCat(count);
    }

    @RequestMapping("/toAddCat")
    public String toAdd() {
        return "addCat_table";
    }

    @RequiresPermissions("admin")
    @RequestMapping("/addCat")
    public String addEmployee(Cat cat, Model model) {
        if (cat.getAdoptDate().equals("")) {
            cat.setAdoptDate("无");
        }
        catMapper.addCat(cat);
        totalPage = (catMapper.catCount() - 1) / 5 + 1;
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", totalPage);
        return "cat_table";
    }

    @RequiresPermissions("admin")
    @RequestMapping("/toUpdateCat/{page}/{id}")
    public String toUpdate(@PathVariable("page") Integer page, @PathVariable("id") Integer id, Model model) {
        Cat cat = catMapper.queryByID(id);
        model.addAttribute("cat", cat);
        model.addAttribute("page", page);
        return "updateCat_table";
    }

    @RequiresPermissions("admin")
    @RequestMapping("/updateCat")
    public String updateEmployee(Cat cat, Integer page, Model model) {
        if (("").equals(cat.getAdoptDate())) {
            cat.setAdoptDate("无");
        }
        catMapper.updateCat(cat);
        if (totalPage == -1) {
            totalPage = (catMapper.catCount() - 1) / 5 + 1;
        }
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", page);
        return "cat_table";
    }

    @RequiresPermissions("admin")
    @RequestMapping("/deleteCat/{page}/{id}")
    public String delete(@PathVariable("page") Integer page, @PathVariable("id") Integer id, Model model) {
        catMapper.deleteCat(id);
        int count = catMapper.catCount();
        totalPage = (count - 1) / 5 + 1;
        if (count % 5 == 0 && count != 0) {
            page--;
        }
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", page);
        return "cat_table";
    }

}
