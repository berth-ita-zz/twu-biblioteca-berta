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
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Test
    public void selectMenuOptionListBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getList()).thenReturn(bookListMocked);
        String bookList = bookService.getList();
        assertThat(bookList).isEqualTo(String.format("%-45s %-20s %-4s\n", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-45s %-20s %-4s\n","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void selectMenuOptionCheckOutBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getList()).thenReturn(bookListMocked);
        String bookList = bookService.printList("2");
        assertThat(bookList).isEqualTo(String.format("%-4s %-45s %-20s %-4s\n", "0001", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-4s %-45s %-20s %-4s\n","0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void checkOutBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        Mockito.when(bookRepository.deleteFromList("2")).thenReturn(true);
        String bookRemoved = bookService.elementOperation("2", "2");
        Mockito.verify(bookRepository).deleteFromList("2");
        assertThat(bookRemoved).isEqualTo("Thank you! Enjoy the book");
    }

    @Test
    public void checkOutBookNotOkTest() {
        BookService bookService = new BookService(bookRepository);
        Mockito.when(bookRepository.deleteFromList("25")).thenReturn(false);
        String bookRemoved = bookService.elementOperation("25", "2");
        Mockito.verify(bookRepository).deleteFromList("25");
        assertThat(bookRemoved).isEqualTo("That book is not available");
    }

    @Test
    public void selectMenuOptionReturnBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        List<Book> bookListMocked = getBooksList();
        Mockito.when(bookRepository.getReturnList()).thenReturn(bookListMocked);
        String bookList = bookService.printList("3");
        assertThat(bookList).isEqualTo(String.format("%-4s %-45s %-20s %-4s\n", "0001", "The Hobbit", "J.R.R Tolkien", 1937)
                + String.format("%-4s %-45s %-20s %-4s\n","0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
    }

    @Test
    public void returnBookOkTest() {
        BookService bookService = new BookService(bookRepository);
        Mockito.when(bookRepository.returnFromList("1")).thenReturn(true);
        String bookReturned = bookService.elementOperation("1", "3");
        Mockito.verify(bookRepository).returnFromList("1");
        assertThat(bookReturned).isEqualTo("Thank you for returning the book");
    }

    @Test
    public void returnBookNotOkTest() {
        BookService bookService = new BookService(bookRepository);
        Mockito.when(bookRepository.returnFromList("15")).thenReturn(false);
        String bookReturned = bookService.elementOperation("15", "3");
        Mockito.verify(bookRepository).returnFromList("15");
        assertThat(bookReturned).isEqualTo("This is not a valid book to return");
    }

    private List<Book> getBooksList() {
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