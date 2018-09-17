package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BookRepositoryTest {

    @Test
    public void getBookListOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> bookList = bookRepository.getList();
        assertThat(bookList).hasSize(5);
    }

    @Test
    public void deleteBookFromListListSizeOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> bookList = bookRepository.getList();
        bookRepository.deleteFromList("2");
        assertThat(bookList).hasSize(4);
    }

    @Test
    public void deleteBookFromListOkTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.deleteFromList("2");
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void deleteBookFromListNotCorrectNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.deleteFromList("25");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListNotNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.deleteFromList("a");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListLimitNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.deleteFromList("6");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListZeroNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.deleteFromList("0");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void returnBookListSizeOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> returnBookList = bookRepository.getReturnList();
        bookRepository.deleteFromList("2");
        assertThat(returnBookList).hasSize(1);
    }

    @Test
    public void returnBookOkTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.deleteFromList("2");
        Boolean bookRemoved = bookRepository.returnFromList("1");
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void returnBookNotCorrectNumberTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.deleteFromList("2");
        Boolean bookRemoved = bookRepository.returnFromList("7");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void returnBookNotNumberTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.deleteFromList("1");
        Boolean bookRemoved = bookRepository.returnFromList("a");
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void returnBookZeroNumberTest() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.deleteFromList("1");
        Boolean bookRemoved = bookRepository.returnFromList("0");
        assertThat(bookRemoved).isFalse();
    }

}
