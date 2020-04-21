package com.mycompany.creditsystem.domain.logic;

public class Credit {

    private String name;
    private int id;

    public Credit (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }


}