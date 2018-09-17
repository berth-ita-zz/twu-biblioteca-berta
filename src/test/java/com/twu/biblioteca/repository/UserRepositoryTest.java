package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;
import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserRepositoryTest {

    @Test
    public void getUserByUserPasswordOkTest() {
        UserRepository userRepository = new UserRepository();
        String libraryNumber = "123-4567";
        String password = "password";
        User user = userRepository.getUserByUserPassword(libraryNumber, password);
        assertThat(user.getLibraryNumber()).isEqualTo(libraryNumber);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void getUserByUserPasswordNotOkTest() {
        UserRepository userRepository = new UserRepository();
        String libraryNumber = "100-4567";
        String password = "pass";
        User user = userRepository.getUserByUserPassword(libraryNumber, password);
        assertThat(user).isNull();
    }
}
