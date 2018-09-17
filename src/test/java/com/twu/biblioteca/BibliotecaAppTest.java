package com.twu.biblioteca;

import com.twu.biblioteca.service.BibliotecaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.BufferedReader;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaAppTest {

    @Mock
    private BibliotecaService bibliotecaService;
    @Mock
    private BufferedReader bufferedReader;

    @Test
    public void selectMenuOptionNotOkTest() throws Exception {
        String invalidOption = BibliotecaApp.selectMenuOptionUserLogged("a");
        assertThat(invalidOption).isEqualTo("Select a valid option!\n");
    }

    @Test
    public void selectMenuOptionListBookOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.selectMenuOptionUserLogged("1");
        Mockito.verify(bibliotecaService).getBooksList();
    }

    @Test
    public void selectMenuOptionQuitOkTest() throws Exception {
        String quit = BibliotecaApp.selectMenuOptionUserLogged("8");
        assertThat(quit).isEqualTo("Quit");
    }

    @Test
    public void selectMenuOptionCheckOutBookEmptyListTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("");
        String bookCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("There are no books available to check out");
    }

    @Test
    public void selectMenuOptionCheckOutBookOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationBook("1", "2", null)).thenReturn("Thank you! Enjoy the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionCheckOutBookNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationBook("7", "2", null)).thenReturn("That book is not available");
        Mockito.when(bibliotecaService.operationBook("1", "2", null)).thenReturn("Thank you! Enjoy the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("2");
        Mockito.verify(bibliotecaService).printBooksList();
        assertThat(bookCheckOutResult).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionListMovieOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.selectMenuOptionUserLogged("4");
        Mockito.verify(bibliotecaService).getMoviesList();
    }

    @Test
    public void selectMenuOptionCheckOutMovieEmptyListTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("");
        String movieCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("There are no movies available to check out");
    }

    @Test
    public void selectMenuOptionCheckOutMoviesOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("1", "5", null)).thenReturn("Thank you! Enjoy the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("Thank you! Enjoy the movie");
    }

    @Test
    public void selectMenuOptionCheckOutMovieNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList()).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("7", "5", null)).thenReturn("That movie is not available");
        Mockito.when(bibliotecaService.operationMovie("1", "5", null)).thenReturn("Thank you! Enjoy the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOptionUserLogged("5");
        Mockito.verify(bibliotecaService).printMoviesList();
        assertThat(movieCheckOutResult).isEqualTo("Thank you! Enjoy the movie");
    }

}
