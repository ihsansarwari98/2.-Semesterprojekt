package com.mycompany.creditsystem.domain;

import java.util.Date;

public abstract class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private Date creationDate;
    private AccessRole accessRole;

    public User (String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.creationDate = new Date();
    }

    @Override
    public String toString() {
        return name + " " + id + " " + creationDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setAccessRole(AccessRole accessRole) {
        this.accessRole = accessRole;
    }

    public AccessRole getAccessRole() {
        return accessRole;
    }
}
