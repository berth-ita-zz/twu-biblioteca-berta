package com.twu.biblioteca.entity;

import com.twu.biblioteca.exception.InvalidLibraryNumberException;
import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserTest {

    @Test
    public void userLibraryNumberOkTest() {
        User user = new User();
        String libraryNumber = "xxx-xxxx";
        user.setLibraryNumber(libraryNumber);
        assertThat(user.getLibraryNumber()).isEquals(libraryNumber);
    }

    @Test(expected = InvalidLibraryNumberException.class)
    public void userLibraryNumberNotOkTest() {
        User user = new User();
        String libraryNumber = "xxxxxxxx";
        user.setLibraryNumber(libraryNumber);
    }

    @Test
    public void userPasswordOkTest() {
        User user = new User();
        String password = "testPassword";
        user.setPassword(password);
        assertThat(user.getPassword()).isEquals(password);
    }

}