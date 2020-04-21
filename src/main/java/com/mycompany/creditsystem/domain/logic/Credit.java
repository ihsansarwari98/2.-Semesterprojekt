package com.mycompany.creditsystem.domain.logic;

public class Credit {
    private String name;
    private String role;
    private User createdBy;

    public Credit (String name, String role, User createdBy) {
        this.name = name;
        this.role = role;
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return name + " is " + role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
