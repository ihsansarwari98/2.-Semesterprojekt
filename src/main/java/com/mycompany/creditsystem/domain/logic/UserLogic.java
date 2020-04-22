package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.domain.interfaces.IUserHandler;
import com.mycompany.creditsystem.persistence.UserHandler;

import java.util.ArrayList;

public class UserLogic implements IUserHandler {

    @Override
    public ArrayList<User> getUsers() {
        return UserHandler.getInstance().getUsers();
    }

    @Override
    public Boolean userLogin(String username, String password) {
        boolean foundUser = false;
        for (int i = 0; i < UserHandler.getInstance().getUsers().size(); i++) {
            User user = UserHandler.getInstance().getUsers().get(i);
            // check if username and password matches
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                foundUser = true;
                CurrentUser.getInstance().setUser(user);
                break;
            }
        }
        return foundUser;
    }

}
