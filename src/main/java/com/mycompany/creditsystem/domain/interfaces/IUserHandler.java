package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.domain.logic.User;

import java.util.ArrayList;

public interface IUserHandler {
    public ArrayList<User> getUsers ();
    public boolean userLogin(String username, String password);
    public boolean createUser(User user);
    public boolean deleteUser(int user_id);

}
