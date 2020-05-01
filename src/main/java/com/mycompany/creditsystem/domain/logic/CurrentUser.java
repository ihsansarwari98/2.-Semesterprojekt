package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.Production;
import com.mycompany.creditsystem.persistence.User;

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
        for (int i = 0; i < searchHistory.size(); i++) {
            if (searchHistory.get(i).getId() == production.getId()) {
                searchHistory.remove(i);
            }
        }
        searchHistory.add(0, production);
    }

    public ArrayList<Production> getMyProductions() {
        return myProductions;
    }

    public void setMyProductions(ArrayList<Production> myProductions) {
        CurrentUser.myProductions = myProductions;
    }

    private boolean nameComparatorShift = true;
    public boolean sortMyProductionsByName() {
        ProductionNameComparator nameComparator = new ProductionNameComparator();
        if (nameComparatorShift) {
            CurrentUser.myProductions.sort(nameComparator);
            nameComparatorShift = false;
            return true;
        } else {
            CurrentUser.myProductions.sort(nameComparator.reversed());
            nameComparatorShift = true;
            return false;
        }
    }

    private boolean deadlineComparatorShift = true;
    public boolean sortMyProductionsByDeadline() {
        ProductionDeadlineComparator deadlineComparator = new ProductionDeadlineComparator();
        if (deadlineComparatorShift) {
            CurrentUser.myProductions.sort(deadlineComparator);
            deadlineComparatorShift = false;
            return true;
        } else {
            CurrentUser.myProductions.sort(deadlineComparator.reversed());
            deadlineComparatorShift = true;
            return false;
        }
    }
}
