package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BibliotecaServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    public void printWelcomeMessageOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        String welcomeMessage = bibliotecaService.getWelcomeMessage();
        assertThat(welcomeMessage).isEqualTo("Welcome to La Biblioteca!");
    }

    @Test
    public void printMainMenuOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        String mainMenuMessage = bibliotecaService.getMainMenuMessage();
        assertThat(mainMenuMessage).isEqualTo("1 - List books");
    }

    @Test
    public void selectMenuOptionOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getBookList()).thenReturn(bookListMocked);
        String bookList = bibliotecaService.selectMenuOption("1");
        assertThat(bookList).isEqualTo(String.format("%-45s %-30s %-4s\n", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-45s %-30s %-4s\n","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));

    }

    @Test
    public void selectMenuOptionNotOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        String invalidOption = bibliotecaService.selectMenuOption("a");
        assertThat(invalidOption).isEqualTo("Select a valid option!");
    }

    private List<Book> getBooksList() {
        List<Book> bookListMocked = new ArrayList<>();
        bookListMocked.add(getBook("The Hobbit", "J.R.R Tolkien", 1937));
        bookListMocked.add(getBook("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
        return bookListMocked;
    }

    private Book getBook(String title, String author, Integer yearPublished) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublished(yearPublished);
        return book;
    }
}