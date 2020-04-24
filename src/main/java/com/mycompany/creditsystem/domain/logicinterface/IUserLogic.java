package com.mycompany.creditsystem.domain.logicinterface;

import com.mycompany.creditsystem.domain.logic.User;

import java.util.ArrayList;

public interface IUserLogic {
    public ArrayList<User> getUsers();
    public boolean createUser(User user);
    public boolean deleteUser(int user_id);
    public boolean userLogin(String username, String password);
    public ArrayList<User> getUsersFromAccessRole(int accessRole);
}

