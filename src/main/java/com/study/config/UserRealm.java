package com.study.config;

import com.study.pojo.User;
import com.study.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;


public class UserRealm extends AuthorizingRealm {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();//拿到User对象，从认证方法里返回值传参获取
        List<String> perms = Arrays.asList(currentUser.getPerms().split(","));
        //设置当前用户的权限
        info.addStringPermissions(perms);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null) {//用户名不存在
            return null; //UnknownAccountException
        }
        //密码认证
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");//把User对象进行传参
    }
}
