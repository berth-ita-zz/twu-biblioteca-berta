package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> userList = new ArrayList<>();

    public UserRepository() {
        userList.add(getUser("123-4567", "password", "Name", "email@email.com", "address", 1234544));
        userList.add(getUser("122-7890", "password1", "Name", "email@email.com", "address", 1234544));
        userList.add(getUser("100-4567", "password", "Name", "email@email.com", "address", 1234544));
    }

    private User getUser(String libraryNumber, String password, String name, String email, String address, Integer phoneNumber) {
        User user = new User();
        user.setLibraryNumber(libraryNumber);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhoneNumber(123446677);
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
