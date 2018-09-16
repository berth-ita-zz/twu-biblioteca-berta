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

    @Test
    public void getBooksListOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        bibliotecaService.getBooksList();
        Mockito.verify(bookService).getBooksList();
    }

    @Test
    public void printBooksListOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        bibliotecaService.printBooksList("2");
        Mockito.verify(bookService).printBooksList("2");
    }

    @Test
    public void operationBookOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        bibliotecaService.operationBook("1", "2");
        Mockito.verify(bookService).operationBook("1", "2");
    }

    @Test
    public void getMoviesListOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        bibliotecaService.getMoviesList();
        Mockito.verify(movieService).getMoviesList();
    }

    @Test
    public void printMoviesListOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        bibliotecaService.printMoviesList();
        Mockito.verify(movieService).printMoviesList();
    }

    @Test
    public void operationMovieOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        bibliotecaService.operationMovie("1");
        Mockito.verify(movieService).operationMovie("1");
    }

}