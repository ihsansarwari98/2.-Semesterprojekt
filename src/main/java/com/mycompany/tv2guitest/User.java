package com.mycompany.tv2guitest;

public class User {
    private String name;
    private String accessRole;

    public User (String name, String accessRole) {
        this.name = name;
        this.accessRole = accessRole;
    }

    @Override
    public String toString() {
        return name + " | " + accessRole;
    }

    public String getName() {
        return name;
    }

    public String getAccessRole() {
        return accessRole;
    }
}
