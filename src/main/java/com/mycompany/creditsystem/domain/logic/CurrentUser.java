package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.Production;
import com.mycompany.creditsystem.persistence.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.jar.Attributes;

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
    public static ArrayList<User> myProducers = new ArrayList<>();
    public static ArrayList<User> myProductionCompanies = new ArrayList<>();
    public static ArrayList<User> allAdmins = new ArrayList<>();
    public static String sortedByStatus = "name";


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

    public ArrayList<User> getMyProducers() {
        return myProducers;
    }

    public ArrayList<User> getMyProductionCompanies() {
        return myProductionCompanies;
    }

    public ArrayList<User> getAllAdmins() {
        return allAdmins;
    }

    public void setMyProducers(ArrayList<User> myProducers) {
        CurrentUser.myProducers = myProducers;
    }

    public void setMyProductionCompanies(ArrayList<User> myProductionCompanies) {
        CurrentUser.myProductionCompanies = myProductionCompanies;
    }

    public void setAllAdmins(ArrayList<User> allAdmins) {
        CurrentUser.allAdmins = allAdmins;
    }

    public void setMyProductions(ArrayList<Production> myProductions) {
        CurrentUser.myProductions = myProductions;
    }

    public String getSortedByStatus() {
        return sortedByStatus;
    }

    public void setSortedByStatus(String sortedByStatus) {
        CurrentUser.sortedByStatus = sortedByStatus;
    }

    private boolean nameComparatorShift = true;
    private boolean deadlineComparatorShift = true;

    public boolean sortByName() {
        if (nameComparatorShift) {
            return sortListsByName();
        } else {
            return sortListsByNameReversed();
        }
    }

    public boolean sortByDeadline() {
        if (deadlineComparatorShift) {
            return sortListsByDeadline();
        } else {
            return sortListsByDeadlineReversed();
        }
    }

    public boolean sortListsByName() {
        NameComparator nameComparator = new NameComparator();
        CurrentUser.myProductions.sort(nameComparator);
        CurrentUser.myProducers.sort(nameComparator);
        CurrentUser.myProductionCompanies.sort(nameComparator);
        CurrentUser.allAdmins.sort(nameComparator);
        nameComparatorShift = false;
        sortedByStatus = "name";
        return true;
    }

    public boolean sortListsByNameReversed() {
        NameComparator nameComparator = new NameComparator();
        CurrentUser.myProductions.sort(nameComparator.reversed());
        CurrentUser.myProducers.sort(nameComparator.reversed());
        CurrentUser.myProductionCompanies.sort(nameComparator.reversed());
        CurrentUser.allAdmins.sort(nameComparator.reversed());
        nameComparatorShift = true;
        sortedByStatus = "nameReversed";
        return false;
    }

    public boolean sortListsByDeadline() {
        ProductionDeadlineComparator deadlineComparator = new ProductionDeadlineComparator();
        CurrentUser.myProductions.sort(deadlineComparator);
        deadlineComparatorShift = false;
        sortedByStatus = "deadline";
        return true;
    }

    public boolean sortListsByDeadlineReversed() {
        ProductionDeadlineComparator deadlineComparator = new ProductionDeadlineComparator();
        CurrentUser.myProductions.sort(deadlineComparator.reversed());
        deadlineComparatorShift = true;
        sortedByStatus = "deadlineReversed";
        return false;
    }
}
