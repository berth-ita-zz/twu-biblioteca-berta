package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
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
        bookRepository.loggedUserCheckOutElement("2", new User());
        assertThat(bookList).hasSize(4);
    }

    @Test
    public void deleteBookFromListOkTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutElement("2", new User());
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void deleteBookFromListNotNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutElement("a", new User());
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListLimitNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutElement("6", new User());
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListZeroNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutElement("0", new User());
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListUserWithBook() {
        BookRepository bookRepository = new BookRepository();
        User user = new User();
        user.setBookCheckedOut(new Book());
        Boolean bookRemoved = bookRepository.loggedUserCheckOutElement("1", user);
        assertThat(bookRemoved).isFalse();

    }

    @Test
    public void returnBookOkTest() {
        BookRepository bookRepository = new BookRepository();
        User user = new User();
        bookRepository.loggedUserCheckOutElement("2", user);
        Boolean bookRemoved = bookRepository.loggedUserReturnElement(user);
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void returnBookNotOkTest() {
        BookRepository bookRepository = new BookRepository();
        User user = new User();
        Boolean bookRemoved = bookRepository.loggedUserReturnElement(user);
        assertThat(bookRemoved).isFalse();
    }

}
