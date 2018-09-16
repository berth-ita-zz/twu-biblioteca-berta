package com.twu.biblioteca.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaServiceTest {

    @Mock
    private BookService bookService;

    @Test
    public void printWelcomeMessageOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService);
        String welcomeMessage = bibliotecaService.getWelcomeMessage();
        assertThat(welcomeMessage).isEqualTo("Welcome to La Biblioteca!");
    }

    @Test
    public void printMainMenuOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService);
        String mainMenuMessage = bibliotecaService.getMainMenuMessage();
        assertThat(mainMenuMessage).isEqualTo("1 - List books\n2 - Check out book\n3 - Return book\n4 - Quit\n");
    }

    @Test
    public void getBooksListTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService);
        bibliotecaService.getBooksList();
        Mockito.verify(bookService).getBooksList();
    }

    @Test
    public void printBooksListTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService);
        bibliotecaService.printBooksList("2");
        Mockito.verify(bookService).printBooksList("2");
    }

    @Test
    public void operationBookTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService);
        bibliotecaService.operationBook("1", "2");
        Mockito.verify(bookService).operationBook("1", "2");
    }

}