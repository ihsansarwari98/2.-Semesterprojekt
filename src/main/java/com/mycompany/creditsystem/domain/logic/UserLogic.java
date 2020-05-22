package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.User;
import com.mycompany.creditsystem.persistence.UserHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserLogic {

    public ArrayList<User> getUsers() {
        return UserHandler.getInstance().getUsers();
    }

    public User getUser(String name) {
        return UserHandler.getInstance().getUser(name);
    }

    public boolean userLogin(String username, String password) {
        boolean foundUser = false;
        CurrentUser.getInstance().setUser(UserHandler.getInstance().getUserLogin(username, password));
        if (UserHandler.getInstance().getUserLogin(username, password) != null) {
            foundUser = true;
        }
        return foundUser;
    }

    public boolean createUser(String name, String username, String password, int accessRole) {

        return UserHandler.getInstance().createUser(new User(name, username, password, accessRole));
    }

    public int getIDFromName(String name) {
        return UserHandler.getInstance().getUser(name).getId();
    }

    public boolean deleteUser(int user_id) {
        return UserHandler.getInstance().deleteUser(user_id);
    }

    public ArrayList<User> getProducersLinkedToProductionCompany(int production_company_id) {
        return UserHandler.getInstance().getProducersLinkedToProductionCompany(production_company_id);
    }

    public ArrayList<User> getPublicUsers() {
        return UserHandler.getInstance().getUsersFromAccessRole(0);
    }

    public ArrayList<User> getPublicUsers(String namePart) {
        return UserHandler.getInstance().getUsersFromAccessRole(0, namePart);
    }

    public ArrayList<User> getProducers() {
        return UserHandler.getInstance().getUsersFromAccessRole(1);
    }

    public ArrayList<User> getProducers(String namePart) {
        return UserHandler.getInstance().getUsersFromAccessRole(1, namePart);
    }

    public ArrayList<User> getProductionCompanies() {
        return UserHandler.getInstance().getUsersFromAccessRole(2);
    }

    public ArrayList<User> getProductionCompanies(String namePart) {
        return UserHandler.getInstance().getUsersFromAccessRole(2, namePart);
    }

    public ArrayList<User> getAdministrators() {
        return UserHandler.getInstance().getUsersFromAccessRole(3);
    }

    public ArrayList<User> getAdministrators(String namePart) {
        return UserHandler.getInstance().getUsersFromAccessRole(3, namePart);
    }

    public boolean linkProducerToCompany(int producer_id, int company_id) {
        return UserHandler.getInstance().linkProducerToCompany(producer_id, company_id);
    }

    public boolean isUsernameTaken(String username) {
        ArrayList<User> users = UserHandler.getInstance().getUsers();
        boolean isTaken = false;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                isTaken = true;
                break;
            }
        }
        return isTaken;
    }
}
