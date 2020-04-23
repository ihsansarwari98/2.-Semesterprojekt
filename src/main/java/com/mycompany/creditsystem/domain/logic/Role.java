package com.mycompany.creditsystem.domain.logic;

public class Role {
    private String title;
    private int id;

    public Role(String title) {
        this.title = title;
    }

    public Role(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return title;
    }
}
