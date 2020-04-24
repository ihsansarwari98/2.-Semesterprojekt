package com.mycompany.creditsystem.domain.logic;

import com.mycompany.creditsystem.domain.interfaces.IUserHandler;
import com.mycompany.creditsystem.domain.logicinterface.IUserLogic;
import com.mycompany.creditsystem.persistence.UserHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserLogic implements IUserLogic {

    @Override
    public ArrayList<User> getUsers() {
        return UserHandler.getInstance().getUsers();
    }

    @Override
    public boolean userLogin(String username, String password) {
        boolean foundUser = false;
        CurrentUser.getInstance().setUser(UserHandler.getInstance().getUserLogin(username, password));
        if (UserHandler.getInstance().getUserLogin(username, password) != null) {
            foundUser = true;
        }
        return foundUser;
    }

    @Override
    public boolean createUser(User user) {
        return UserHandler.getInstance().createUser(user);
    }

    @Override
    public boolean deleteUser(int user_id) {
        return UserHandler.getInstance().deleteUser(user_id);
    }

    @Override
    public ArrayList<User> getUsersFromAccessRole(int accessRole) {
        return UserHandler.getInstance().getUsersFromAccessRole(accessRole);
    }
}
