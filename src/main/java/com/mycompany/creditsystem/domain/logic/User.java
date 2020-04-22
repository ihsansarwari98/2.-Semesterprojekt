package com.mycompany.creditsystem.domain.logic;

import java.util.Date;

public class User {
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
        this.accessRole = AccessRole.publicUser;
    }

    public User (int id, String name, String username, String password, Date creationDate, int accessRole) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
        this.accessRole = calcAccessRole(accessRole);
    }

    private AccessRole calcAccessRole(int accessRoleNumber) {
        if (accessRoleNumber == 1) {
            return AccessRole.producer;
        } else if (accessRoleNumber == 2) {
            return AccessRole.productionCompany;
        } else if (accessRoleNumber == 3) {
            return AccessRole.admin;
        }
        return AccessRole.publicUser;
    }

    public enum AccessRole {
        publicUser,
        producer,
        productionCompany,
        admin
    }

    @Override
    public String toString() {
        return name + " " + id + " " + username + " " + password + " " + creationDate + " " + accessRole;
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

    public AccessRole getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(AccessRole accessRole) {
        this.accessRole = accessRole;
    }
}
