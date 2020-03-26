package com.mycompany.tv2guitest;

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
}
