package com.study.controller;

import com.study.mapper.UserMapper;
import com.study.pojo.User;
import com.study.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model, HttpSession httpSession) {
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token); //执行登录方法
            httpSession.setAttribute("loginUser", username);
            return "redirect:/index";
        } catch (UnknownAccountException e) {//用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {//密码不存在
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

    @RequestMapping("/noPermission")
    public String noPermission(){
        return "error/no_permission";
    }

    /*@RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }*/

}
