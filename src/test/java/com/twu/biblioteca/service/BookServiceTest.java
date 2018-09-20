package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
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
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    public void selectMenuOptionToListAllBooksAvailable() {
        BookService bookService = new BookService(bookRepository);
        List<Book> bookListMocked = getBooksListMocked();
        Mockito.when(bookRepository.getList()).thenReturn(bookListMocked);
        String bookList = bookService.getList();
        assertThat(bookList).isEqualTo(String.format("%-45s %-20s %-4s\n", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-45s %-20s %-4s\n","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void selectMenuOptionToCheckOutBookAndSeeListBooks() {
        BookService bookService = new BookService(bookRepository);
        List<Book> bookListMocked = getBooksListMocked();
        Mockito.when(bookRepository.getList()).thenReturn(bookListMocked);
        String bookList = bookService.printList();
        assertThat(bookList).isEqualTo(String.format("%-4s %-45s %-20s %-4s\n", "0001", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-4s %-45s %-20s %-4s\n","0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void selectMenuOptionToCheckOutBookAndCheckItOut() {
        BookService bookService = new BookService(bookRepository);
        User user = new User();
        Mockito.when(bookRepository.loggedUserCheckOutElement("2", user)).thenReturn(true);
        String bookRemoved = bookService.elementOperation("2", "2", user);
        Mockito.verify(bookRepository).loggedUserCheckOutElement("2", user);
        assertThat(bookRemoved).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void selectMenuOptionToCheckOutBookAndCheckAnIncorrectOneOut() {
        BookService bookService = new BookService(bookRepository);
        User user = new User();
        Mockito.when(bookRepository.loggedUserCheckOutElement("25", user)).thenReturn(false);
        String bookRemoved = bookService.elementOperation("25", "2", user);
        Mockito.verify(bookRepository).loggedUserCheckOutElement("25", user);
        assertThat(bookRemoved).isEqualTo("That book is not available");
    }

    @Test
    public void returnCheckedOutBookSuccessfully() {
        BookService bookService = new BookService(bookRepository);
        User user = new User();
        Mockito.when(bookRepository.loggedUserReturnElement(user)).thenReturn(true);
        String bookReturned = bookService.elementOperation("1", "3", user);
        Mockito.verify(bookRepository).loggedUserReturnElement(user);
        assertThat(bookReturned).isEqualTo("Thank you for returning the book");
    }

    @Test
    public void returnAnIncorrectBook() {
        BookService bookService = new BookService(bookRepository);
        User user = new User();
        Mockito.when(bookRepository.loggedUserReturnElement(user)).thenReturn(false);
        String bookReturned = bookService.elementOperation("15", "3", user);
        Mockito.verify(bookRepository).loggedUserReturnElement(user);
        assertThat(bookReturned).isEqualTo("This is not a valid book to return");
    }

    private List<Book> getBooksListMocked() {
        List<Book> bookListMocked = new ArrayList<>();
        bookListMocked.add(getBook("0001","The Hobbit", "J.R.R Tolkien", 1937));
        bookListMocked.add(getBook("0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
        return bookListMocked;
    }

    private Book getBook(String id, String title, String author, Integer yearPublished) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublished(yearPublished);
        return book;
    }

}