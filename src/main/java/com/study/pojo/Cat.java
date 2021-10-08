package com.study.pojo;

import java.util.Date;

public class Cat {

    private Integer id;
    private String area;
    private String username;
    private String adoptDate;

    public Cat() {
    }

    public Cat(Integer id, String area, String username, String adoptDate) {
        this.id = id;
        this.area = area;
        this.username = username;
        this.adoptDate = adoptDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdoptDate() {
        return adoptDate;
    }

    public void setAdoptDate(String adoptDate) {
        this.adoptDate = adoptDate;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", username='" + username + '\'' +
                ", adoptDate='" + adoptDate + '\'' +
                '}';
    }
}
