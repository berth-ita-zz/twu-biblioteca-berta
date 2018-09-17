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

    @Test
    public void printWelcomeMessageOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        String welcomeMessage = bibliotecaService.getWelcomeMessage();
        assertThat(welcomeMessage).isEqualTo("Welcome to La Biblioteca!");
    }

    @Test
    public void printMainMenuOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        String mainMenuMessage = bibliotecaService.getMainMenuMessage();
        assertThat(mainMenuMessage).isEqualTo("1 - List books\n2 - Check out book\n3 - Return book\n4 - List movies\n5 - Quit\n");
    }

}