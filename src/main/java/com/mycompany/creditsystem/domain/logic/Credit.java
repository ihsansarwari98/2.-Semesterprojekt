package com.mycompany.creditsystem.domain.logic;

public class Credit {
    String name;
    String role;

    public Credit (String name, String role) {
        this.name = name;
        this.role = role;
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
}
