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
    public void selectMenuOptionListBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getBookList()).thenReturn(bookListMocked);
        String bookList = bookService.getBooksList();
        assertThat(bookList).isEqualTo(String.format("%-45s %-20s %-4s\n", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-45s %-20s %-4s\n","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void selectMenuOptionCheckOutBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getBookList()).thenReturn(bookListMocked);
        String bookList = bookService.printBooksList();
        assertThat(bookList).isEqualTo(String.format("%-2s. %-45s %-20s %-4s\n", "1", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-2s. %-45s %-20s %-4s\n","2","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void checkOutBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        Mockito.when(bookRepository.loggedUserCheckOutBook("2", new User())).thenReturn(true);
        String bookRemoved = bookService.operationBook("2", "2", new User());
        Mockito.verify(bookRepository).loggedUserCheckOutBook("2", new User());
        assertThat(bookRemoved).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void checkOutBookNotOkTest() {
        BookService bookService = new BookService(bookRepository);
        Mockito.when(bookRepository.loggedUserCheckOutBook("25", new User())).thenReturn(false);
        String bookRemoved = bookService.operationBook("25", "2", new User());
        Mockito.verify(bookRepository).loggedUserCheckOutBook("25", new User());
        assertThat(bookRemoved).isEqualTo("That book is not available");
    }

    @Test
    public void returnBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        Mockito.when(bookRepository.loggedUserReturnBook(new User())).thenReturn(true);
        String bookReturned = bookService.operationBook("1", "3", new User());
        Mockito.verify(bookRepository).loggedUserReturnBook(new User());
        assertThat(bookReturned).isEqualTo("Thank you for returning the book");
    }

    @Test
    public void returnBookNotOkTest() {
        BookService bookService = new BookService(bookRepository);
        Mockito.when(bookRepository.loggedUserReturnBook(new User())).thenReturn(false);
        String bookReturned = bookService.operationBook("15", "3", new User());
        Mockito.verify(bookRepository).loggedUserReturnBook(new User());
        assertThat(bookReturned).isEqualTo("You do not have any book to return");
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
        book.setYear(yearPublished);
        return book;
    }

}