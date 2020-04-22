package com.mycompany.creditsystem.domain.logic;

import java.util.ArrayList;

public class CurrentUser {

    public static CurrentUser instance;

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public static User user;
    public static ArrayList<Production> searchHistory = new ArrayList<>();
    public static ArrayList<Production> myProductions = new ArrayList<>();

    public User getUser() {
        return CurrentUser.user;
    }

    public void setUser(User user) {
        CurrentUser.user = user;
    }

    public ArrayList<Production> getSearchHistory() {
        return CurrentUser.searchHistory;
    }

    public void addToSearchHistory(Production production) {
        for (int i = 0; i < CurrentUser.searchHistory.size(); i++) {
            if (CurrentUser.searchHistory.get(i).getId() == production.getId()) {
                CurrentUser.searchHistory.remove(i);
            }
        }
        CurrentUser.searchHistory.add(0, production);
    }

    public ArrayList<Production> getMyProductions() {
        return myProductions;
    }

    public void setMyProductions(ArrayList<Production> myProductions) {
        CurrentUser.myProductions = myProductions;

    }
}
