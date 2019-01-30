package com.matt.Keyword.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int id;

    @Size(min=3, message = "Try again")
    private String email;

    @Size(min=1, message = "A password is required")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Account> accounts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private List<Mod> mods;

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    public List<Mod> getMods() {
        return mods;
    }

    public void setMods(List<Mod> mods) {
        this.mods = mods;
    }


    @Override
    public String toString()
    {
        return email + " " + password;
    }


}