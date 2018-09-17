package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Test
    public void userLogInOkTest() {
        UserService userService = new UserService(userRepository);
        User userMocked = getUser();
        Mockito.when(userRepository.getUserByUserPassword(userMocked.getLibraryNumber(), userMocked.getPassword())).thenReturn(userMocked);
        User user = userService.userLogIn(userMocked.getLibraryNumber(), userMocked.getPassword());
        assertThat(user).isEqualToComparingFieldByFieldRecursively(userMocked);
    }

    @Test
    public void userLogInNotOkTest() {
        UserService userService = new UserService(userRepository);
        User userMocked = getUser();
        User user = userService.userLogIn(userMocked.getLibraryNumber(), userMocked.getPassword());
        assertThat(user).isNull();
    }

    @Test
    public void userProfileInformationOkTest() {
        UserService userService = new UserService(userRepository);
        User userMocked = getUser();
        String userData = userService.getUserProfileInformation(userMocked);
        assertThat(userData).isEqualTo("Name: " + userMocked.getName() + "\nEmail: " + userMocked.getEmail() +
                "\nAddress: " + userMocked.getAddress() + "\nPhone Number: " + userMocked.getPhoneNumber());
    }

    private User getUser() {
        User user = new User();
        user.setLibraryNumber("123-4567");
        user.setPassword("password");
        user.setName("User Name");
        user.setEmail("userEmail@email.com");
        user.setAddress("User Address");
        user.setPhoneNumber(930001122);
        return user;
    }

}
