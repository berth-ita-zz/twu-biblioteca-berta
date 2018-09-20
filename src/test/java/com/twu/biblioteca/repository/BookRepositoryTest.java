package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BookRepositoryTest {

    @Test
    public void checkOutBookSuccessfully() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookCheckedOut = bookRepository.loggedUserCheckOutElement("0001", new User());
        assertThat(bookCheckedOut).isTrue();
    }

    @Test
    public void checkOutBookWithIncorrectId() {
        BookRepository bookRepository = new BookRepository();
        Boolean bookCheckedOut = bookRepository.loggedUserCheckOutElement("a", new User());
        assertThat(bookCheckedOut).isFalse();
    }

    @Test
    public void checkOutBookWhenUserAlreadyCheckedOutOne() {
        BookRepository bookRepository = new BookRepository();
        User user = new User();
        user.setBookCheckedOut(new Book());
        Boolean bookCheckedOut = bookRepository.loggedUserCheckOutElement("1", user);
        assertThat(bookCheckedOut).isFalse();
    }

    @Test
    public void userReturnBookSuccessfully() {
        BookRepository bookRepository = new BookRepository();
        User user = new User();
        bookRepository.loggedUserCheckOutElement("0012", user);
        Boolean bookCheckedOut = bookRepository.loggedUserReturnElement(user);
        assertThat(bookCheckedOut).isTrue();
    }

    @Test
    public void userReturnBookIncorrect() {
        BookRepository bookRepository = new BookRepository();
        User user = new User();
        Boolean bookCheckedOut = bookRepository.loggedUserReturnElement(user);
        assertThat(bookCheckedOut).isFalse();
    }

}
