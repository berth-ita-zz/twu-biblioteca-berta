package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> userList = new ArrayList<>();

    public UserRepository() {
        userList.add(getUser("123-4567", "password"));
        userList.add(getUser("122-7890", "password1"));
        userList.add(getUser("100-4567", "password"));
    }

    private User getUser(String libraryNumber, String password) {
        User user = new User();
        user.setLibraryNumber(libraryNumber);
        user.setPassword(password);
        return user;
    }

    public User getUserByUserPassword(String libraryNumber, String password) {
        for (User user : userList) {
            if(user.getLibraryNumber().equals(libraryNumber) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

}
