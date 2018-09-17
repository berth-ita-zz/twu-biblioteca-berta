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
        List<Book> bookList = bookRepository.getBookList();
        assertThat(bookList).hasSize(5);
    }

    @Test
    public void deleteBookLoggedUserOkTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutBook("2", new User());
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void deleteBookNotLoggedUserOkTest() {
        BookRepository bookRepository = new BookRepository();
        User user = getUser();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutBook("2", user);
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListListSizeOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> bookList = bookRepository.getBookList();
        bookRepository.loggedUserCheckOutBook("2", new User());
        assertThat(bookList).hasSize(4);
    }

    @Test
    public void deleteBookFromListNotCorrectNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutBook("25", new User());
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListNotNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutBook("a", new User());
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListLimitNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutBook("6", new User());
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListZeroNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutBook("0", new User());
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListNegativeNumberTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserCheckOutBook("-5", new User());
        assertThat(bookRemoved).isFalse();
    }

    @Test
    public void returnBookLoggedUserOkTest() {
        BookRepository bookRepository = new BookRepository();
        User user = new User();
        bookRepository.loggedUserCheckOutBook("2", user);
        Boolean bookRemoved = bookRepository.loggedUserReturnBook(user);
        assertThat(bookRemoved).isTrue();
    }

    @Test
    public void returnBookNotLoggedUserOkTest() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookRemoved = bookRepository.loggedUserReturnBook(new User());
        assertThat(bookRemoved).isFalse();
    }

    private User getUser() {
        User user = new User();
        user.setLibraryNumber("123-4567");
        user.setPassword("password");
        user.setBookCheckedOut(new Book());
        return user;
    }

}