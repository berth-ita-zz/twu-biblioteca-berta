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
    public void printBooksListMessageOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getBookList()).thenReturn(bookListMocked);
        String bookList = bibliotecaService.getBooksList();
        assertThat(bookList).isEqualTo("The Hobbit\nThe Hitchhiker's Guide to the Galaxy\n");
    }

    private List<Book> getBooksList() {
        List<Book> bookListMocked = new ArrayList<>();
        bookListMocked.add(getBook("The Hobbit"));
        bookListMocked.add(getBook("The Hitchhiker's Guide to the Galaxy"));
        return bookListMocked;
    }

    private Book getBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        return book;
    }
}