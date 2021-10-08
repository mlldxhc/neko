package com.study.config;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /*
         * anon：无需认证就可访问
         * authc：必须认证才能访问
         * user：必须拥有 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         * */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/login","anon");
        filterMap.put("/","anon");
        filterMap.put("/index.html","anon");
        filterMap.put("/assets/**","anon");
        filterMap.put("/css/**","anon");
        filterMap.put("/img/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/user/logout","logout");
        filterMap.put("/**","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置未授权页面，不设置默认错误页面很丑
        bean.setUnauthorizedUrl("/noPermission");
        //设置登录页
        bean.setLoginUrl("/");
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
