package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BookRepositoryTest {

    @Test
    public void getBookListOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> bookList = bookRepository.getBookList();
        assertThat(bookList).hasSize(5);
    }

    @Test
    public void deleteBookFromListListSizeOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> bookList = bookRepository.getBookList();
        bookRepository.deleteBookFromList("2");
        assertThat(bookList).hasSize(4);
    }

    @Test
    public void deleteBookFromListOkTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList();
        Boolean bookRemoved = bookRepository.deleteBookFromList("2");
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void deleteBookFromListNotCorrectNumberTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList();
        Boolean bookRemoved = bookRepository.deleteBookFromList("25");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListNotNumberTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList();
        Boolean bookRemoved = bookRepository.deleteBookFromList("a");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListLimitNumberTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList();
        Boolean bookRemoved = bookRepository.deleteBookFromList("6");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListZeroNumberTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList();
        Boolean bookRemoved = bookRepository.deleteBookFromList("0");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListNegativeNumberTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList();
        Boolean bookRemoved = bookRepository.deleteBookFromList("-5");
        assertThat(bookRemoved).isFalse();
    }

}