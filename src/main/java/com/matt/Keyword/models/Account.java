package com.matt.Keyword.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, message = "Try again")
    private String name;

    @NotNull
    @Size(min = 1, message = "A password is required")
    private String password;

    @Column(name = "user_id")
    private Integer userid;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account() {
    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

}