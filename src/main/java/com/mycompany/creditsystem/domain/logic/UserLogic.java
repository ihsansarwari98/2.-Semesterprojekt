package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.persistence.User;
import com.mycompany.creditsystem.persistence.UserHandler;

import java.util.ArrayList;

public class UserLogic {

    public ArrayList<User> getUsers() {
        return UserHandler.getInstance().getUsers();
    }

    public boolean userLogin(String username, String password) {
        boolean foundUser = false;
        CurrentUser.getInstance().setUser(UserHandler.getInstance().getUserLogin(username, password));
        if (UserHandler.getInstance().getUserLogin(username, password) != null) {
            foundUser = true;
        }
        return foundUser;
    }

    public boolean createUser(User user) {
        return UserHandler.getInstance().createUser(user);
    }

    public boolean deleteUser(int user_id) {
        return UserHandler.getInstance().deleteUser(user_id);
    }

    public ArrayList<User> getUsersFromAccessRole(int accessRole) {
        return UserHandler.getInstance().getUsersFromAccessRole(accessRole);
    }
}
