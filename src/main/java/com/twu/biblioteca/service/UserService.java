package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userLogIn(String libraryNumber, String password) {
        return userRepository.getUserByUserPassword(libraryNumber, password);
    }

}
