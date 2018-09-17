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
        String quit = BibliotecaApp.selectMenuOption("7");
        assertThat(quit).isEqualTo("Quit");
    }

    @Test
    public void selectMenuOptionCheckOutBookEmptyListTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList("2")).thenReturn("");
        String bookCheckOutResult = BibliotecaApp.selectMenuOption("2");
        Mockito.verify(bibliotecaService).printBooksList("2");
        assertThat(bookCheckOutResult).isEqualTo("There are no books available to check out");
    }

    @Test
    public void selectMenuOptionCheckOutBookOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList("2")).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationBook("1", "2")).thenReturn("Thank you! Enjoy the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOption("2");
        Mockito.verify(bibliotecaService).printBooksList("2");
        assertThat(bookCheckOutResult).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionCheckOutBookNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList("2")).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationBook("7", "2")).thenReturn("That book is not available");
        Mockito.when(bibliotecaService.operationBook("1", "2")).thenReturn("Thank you! Enjoy the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOption("2");
        Mockito.verify(bibliotecaService).printBooksList("2");
        assertThat(bookCheckOutResult).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionReturnBookEmptyListTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList("3")).thenReturn("");
        String bookReturnResult = BibliotecaApp.selectMenuOption("3");
        Mockito.verify(bibliotecaService).printBooksList("3");
        assertThat(bookReturnResult).isEqualTo("There are no books available to return");
    }

    @Test
    public void selectMenuOptionReturnBookOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList("3")).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationBook("1", "3")).thenReturn("Thank you for returning the book");
        String bookReturnResult = BibliotecaApp.selectMenuOption("3");
        Mockito.verify(bibliotecaService).printBooksList("3");
        assertThat(bookReturnResult).isEqualTo("Thank you for returning the book");
    }

    @Test
    public void selectMenuOptionReturnBookNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printBooksList("3")).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationBook("7", "3")).thenReturn("This is not a valid book to return");
        Mockito.when(bibliotecaService.operationBook("1", "3")).thenReturn("Thank you for returning the book");
        String bookCheckOutResult = BibliotecaApp.selectMenuOption("3");
        Mockito.verify(bibliotecaService).printBooksList("3");
        assertThat(bookCheckOutResult).isEqualTo("Thank you for returning the book");
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
        Mockito.when(bibliotecaService.printMoviesList("5")).thenReturn("");
        String movieCheckOutResult = BibliotecaApp.selectMenuOption("5");
        Mockito.verify(bibliotecaService).printMoviesList("5");
        assertThat(movieCheckOutResult).isEqualTo("There are no movies available to check out");
    }

    @Test
    public void selectMenuOptionCheckOutMoviesOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList("5")).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("1", "5")).thenReturn("Thank you! Enjoy the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOption("5");
        Mockito.verify(bibliotecaService).printMoviesList("5");
        assertThat(movieCheckOutResult).isEqualTo("Thank you! Enjoy the movie");
    }

    @Test
    public void selectMenuOptionCheckOutMovieNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList("5")).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("7", "5")).thenReturn("That movie is not available");
        Mockito.when(bibliotecaService.operationMovie("1", "5")).thenReturn("Thank you! Enjoy the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOption("5");
        Mockito.verify(bibliotecaService).printMoviesList("5");
        assertThat(movieCheckOutResult).isEqualTo("Thank you! Enjoy the movie");
    }

    @Test
    public void selectMenuOptionReturnMovieEmptyListTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList("6")).thenReturn("");
        String movieReturnResult = BibliotecaApp.selectMenuOption("6");
        Mockito.verify(bibliotecaService).printMoviesList("6");
        assertThat(movieReturnResult).isEqualTo("There are no movies available to return");
    }

    @Test
    public void selectMenuOptionReturnMovieOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList("6")).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("1", "6")).thenReturn("Thank you for returning the movie");
        String movieReturnResult = BibliotecaApp.selectMenuOption("6");
        Mockito.verify(bibliotecaService).printMoviesList("6");
        assertThat(movieReturnResult).isEqualTo("Thank you for returning the movie");
    }

    @Test
    public void selectMenuOptionReturnMovieNotOkTest() throws Exception {
        BibliotecaApp.bibliotecaService = bibliotecaService;
        BibliotecaApp.bufferedReader = bufferedReader;
        Mockito.when(bibliotecaService.printMoviesList("6")).thenReturn("a");
        Mockito.when(bufferedReader.readLine()).thenReturn("7").thenReturn("1");
        Mockito.when(bibliotecaService.operationMovie("7", "6")).thenReturn("This is not a valid movie to return");
        Mockito.when(bibliotecaService.operationMovie("1", "6")).thenReturn("Thank you for returning the movie");
        String movieCheckOutResult = BibliotecaApp.selectMenuOption("6");
        Mockito.verify(bibliotecaService).printMoviesList("6");
        assertThat(movieCheckOutResult).isEqualTo("Thank you for returning the movie");
    }

}
