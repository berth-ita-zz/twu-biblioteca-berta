package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.util.FileReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    @Mock
    private FileReader fileReader;

    @Test
    public void selectUserHavingLibraryNumberAndPassword() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("123-4567;password;User 1;user1@email.com;address;622222222").thenReturn(null);
        UserRepository userRepository = new UserRepository(fileReader);
        String libraryNumber = "123-4567";
        String password = "password";
        User user = userRepository.getUserByUserPassword(libraryNumber, password);
        assertThat(user.getLibraryNumber()).isEqualTo(libraryNumber);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void selectUserHavingIncorrectLibraryNumberAndPassword() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("123-4567;password;User 1;user1@email.com;address;622222222").thenReturn(null);
        UserRepository userRepository = new UserRepository(fileReader);
        String libraryNumber = "100-4567";
        String password = "pass";
        User user = userRepository.getUserByUserPassword(libraryNumber, password);
        assertThat(user).isNull();
    }

}
