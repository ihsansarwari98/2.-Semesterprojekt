package com.mycompany.creditsystem.domain.interfaces;

import com.mycompany.creditsystem.persistence.User;

import java.util.ArrayList;

public interface IUserHandler {
    public ArrayList<User> getUsers();
    public boolean createUser(User user);
    public boolean deleteUser(int user_id);
    public User getUserLogin(String username, String password);
    public ArrayList<User> getUsersFromAccessRole(int accessRole);
}
