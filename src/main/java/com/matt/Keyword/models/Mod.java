package com.matt.Keyword.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Mods")

public class Mod {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private Integer role;

    @NotNull
    @Size(min = 1, message = "An entry is required")
    private String entry;

    @Column(name = "user_id")
    private Integer userid;

    public Mod(Integer role, String entry) {
        this.role = role;
        this.entry = entry;
    }

    public Mod() {
    }

    public int getId() {

        return id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid= userid;
    }

}
