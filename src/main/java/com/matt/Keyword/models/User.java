package com.matt.Keyword.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;


    @Size(min=3, message = "Try again")
    private String email;


    @Size(min=1, message = "A password is required")
    private String password;

    @OneToMany(mappedBy="user")
    private List<Account> accounts = new ArrayList<>();


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {}

    public int getId() {

        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }
    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    @Override
    public String toString()
    {
        return email + " " + password;
    }


}