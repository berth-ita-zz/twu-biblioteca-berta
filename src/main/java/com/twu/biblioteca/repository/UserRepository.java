package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> userList = new ArrayList<>();

    public UserRepository() {
        userList.add(getUser("123-4567", "password", "User 1", "user1@email.com",
                "address", 622222222));
        userList.add(getUser("122-7890", "password1", "User 2", "user2@email.com",
                "address", 633333333));
        userList.add(getUser("100-4567", "password2", "User 3", "user3@email.com",
                "address", 644444444));
    }

    private User getUser(String libraryNumber, String password, String name, String email, String address, Integer phoneNumber) {
        User user = new User();
        user.setLibraryNumber(libraryNumber);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
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
