package com.twu.biblioteca;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.service.BibliotecaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.BufferedReader;
import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    @Mock
    private BibliotecaService bibliotecaService;
    @Mock
    private BufferedReader bufferedReader;

    @Test
    public void selectMenuOptionNotOkTest() throws Exception {
        String invalidOption = BibliotecaApp.selectMenuOption("a");
        assertThat(invalidOption).isEqualTo("Select a valid option!\n");
    }

    @Test
    public void selectMenuOptionListBookOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.selectMenuOption("1");
        Mockito.verify(bibliotecaService).getBooksList();
    }

    @Test
    public void selectMenuOptionQuitOkTest() throws Exception {
        String quit = BibliotecaApp.selectMenuOption("6");
        assertThat(quit).isEqualTo("Quit");
    }

    @Test
    public void logInUserNotOkTest() throws IOException {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        String result = BibliotecaApp.selectMenuOption("2");
        assertThat(result).isEqualTo("This is not a valid user");
    }

    @Test
    public void selectMenuOptionCheckOutBookEmptyListTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bufferedReader.readLine()).thenReturn("123-4567").thenReturn("password");
        Mockito.when(bibliotecaService.userLogIn("123-4567", "password")).thenReturn(new User());
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("");
        String bookCheckOutResult = BibliotecaApp.selectMenuOption("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("There are no books available to check out");
    }

    @Test
    public void selectMenuOptionCheckOutBookOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bufferedReader.readLine()).thenReturn("123-4567").thenReturn("password").thenReturn("1");
        Mockito.when(bibliotecaService.userLogIn("123-4567", "password")).thenReturn(new User());
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("a");
        Mockito.when(bibliotecaService.operationBook("1", "2", new User())).thenReturn("Thank you! Enjoy the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOption("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionCheckOutBookNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bufferedReader.readLine()).thenReturn("123-4567").thenReturn("password").thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.userLogIn("123-4567", "password")).thenReturn(new User());
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("a");
        Mockito.when(bibliotecaService.operationBook("7", "2", new User())).thenReturn("That book is not available");
        Mockito.when(bibliotecaService.operationBook("1", "2", new User())).thenReturn("Thank you! Enjoy the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOption("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionReturnBookOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bufferedReader.readLine()).thenReturn("123-4567").thenReturn("password").thenReturn("1").thenReturn("123-4567").thenReturn("password");
        Mockito.when(bibliotecaService.userLogIn("123-4567", "password")).thenReturn(new User());
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("a");
        Mockito.when(bibliotecaService.operationBook("1", "2", new User())).thenReturn("Thank you! Enjoy the book");
        BibliotecaApp.selectMenuOption("2");
        Mockito.when(bibliotecaService.operationBook("", "3", new User())).thenReturn("Thank you for returning the book");
        String bookReturnResult = BibliotecaApp.selectMenuOption("3");
        assertThat(bookReturnResult).isEqualTo("Thank you for returning the book");
    }

    @Test
    public void selectMenuOptionReturnBookNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bufferedReader.readLine()).thenReturn("100-4567").thenReturn("password");
        Mockito.when(bibliotecaService.userLogIn("100-4567", "password")).thenReturn(new User());
        Mockito.when(bibliotecaService.operationBook("", "3", new User())).thenReturn("This is not a valid book to return");
        String bookReturnResult = BibliotecaApp.selectMenuOption("3");
        assertThat(bookReturnResult).isEqualTo("This is not a valid book to return");
    }

    @Test
    public void selectMenuOptionListMovieOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.selectMenuOption("4");
        Mockito.verify(bibliotecaService).getMoviesList();
    }

    @Test
    public void selectMenuOptionCheckOutMovieEmptyListTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("");
        String movieCheckOutResult = BibliotecaApp.selectMenuOption("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("There are no movies available to check out");
    }

    @Test
    public void selectMenuOptionCheckOutMovieOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("1")).thenReturn("Thank you! Enjoy the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOption("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("Thank you! Enjoy the movie");
    }

    @Test
    public void selectMenuOptionCheckOutMovieNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("7")).thenReturn("That movie is not available");
        Mockito.when(bibliotecaService.operationMovie("1")).thenReturn("Thank you! Enjoy the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOption("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("Thank you! Enjoy the movie");
    }

}
