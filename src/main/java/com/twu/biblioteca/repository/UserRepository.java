package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.util.FileReader;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> userList = new ArrayList<>();

    public UserRepository(FileReader fileReader) throws Exception {
        String line;
        while ((line = fileReader.readLine()) != null) {
            String[] lineSplit = line.split(";");
            userList.add(getUser(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4],Integer.valueOf(lineSplit[5])));
        }
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
