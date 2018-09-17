package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;
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
    @Mock
    private UserService userService;

    @Test
    public void printWelcomeMessageOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        String welcomeMessage = bibliotecaService.getWelcomeMessage();
        assertThat(welcomeMessage).isEqualTo("Welcome to La Biblioteca!");
    }

    @Test
    public void printMainMenuOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        String mainMenuMessage = bibliotecaService.getMainMenuMessage();
        assertThat(mainMenuMessage).isEqualTo("1 - List books\n2 - Check out book\n3 - Return book\n4 - List movies" +
                "\n5 - Check out movie\n6 - Profile\n7 - Quit");
    }

    @Test
    public void getBooksListOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        bibliotecaService.getBooksList();
        Mockito.verify(bookService).getBooksList();
    }

    @Test
    public void printBooksListOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        bibliotecaService.printBooksList();
        Mockito.verify(bookService).printBooksList();
    }

    @Test
    public void operationBookOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        User user = new User();
        bibliotecaService.operationBook("1", "2", user);
        Mockito.verify(bookService).operationBook("1", "2", user);
    }

    @Test
    public void getMoviesListOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        bibliotecaService.getMoviesList();
        Mockito.verify(movieService).getMoviesList();
    }

    @Test
    public void printMoviesListOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        bibliotecaService.printMoviesList();
        Mockito.verify(movieService).printMoviesList();
    }

    @Test
    public void operationMovieOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        bibliotecaService.operationMovie("1");
        Mockito.verify(movieService).operationMovie("1");
    }

    @Test
    public void userLogInOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        bibliotecaService.userLogIn("123-4567", "password");
        Mockito.verify(userService).userLogIn("123-4567", "password");
    }

    @Test
    public void userProfileInformationOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService, userService);
        bibliotecaService.getUserProfile(new User());
        Mockito.verify(userService).getUserProfileInformation(new User());
    }

}