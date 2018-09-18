package com.twu.biblioteca.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaServiceTest {

    @Mock
    private BookService bookService;
    @Mock
    private MovieService movieService;
    @Mock
    private UserService userService;

    @Test
    public void printWelcomeMessageOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        String welcomeMessage = bibliotecaService.getWelcomeMessage();
        assertThat(welcomeMessage).isEqualTo("Welcome to La Biblioteca!");
    }

    @Test
    public void printMainMenuUserLoggedOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        String mainMenuMessage = bibliotecaService.getMainMenuMessageUserLogged();
        assertThat(mainMenuMessage).isEqualTo("1 - List books\n2 - Check out book\n3 - Return book\n4 - List movies\n" +
                "5 - Check out movie\n6 - Return movie\n7 - Profile information\n8 - Log out\n9 - Quit\n");
    }

    @Test
    public void printMainMenuUserNotLoggedOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        String mainMenuMessage = bibliotecaService.getMainMenuMessageUserNotLogged();
        assertThat(mainMenuMessage).isEqualTo("1 - List books\n2 - List movies\n3 - Log in\n4 - Quit\n");
    }

}