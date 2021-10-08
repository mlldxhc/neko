package com.study.pojo;


public class User {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private Integer cats;
    private String perms;

    public User() {
    }

    public User(Integer id, String username, String password, String name, String phone, Integer cats, String perms) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.cats = cats;
        this.perms = perms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getcats() {
        return cats;
    }

    public void setcats(Integer cats) {
        this.cats = cats;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", cats=" + cats +
                ", perms='" + perms + '\'' +
                '}';
    }
}
