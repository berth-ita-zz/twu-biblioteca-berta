package com.twu.biblioteca.entity;

import com.twu.biblioteca.exception.InvalidLibraryNumberException;
import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserTest {

    @Test
    public void userLibraryNumberOkTest() {
        User user = new User();
        String libraryNumber = "xxx-xxxx";
        user.setLibraryNumber(libraryNumber);
        assertThat(user.getLibraryNumber()).isEqualTo(libraryNumber);
    }

    @Test(expected = InvalidLibraryNumberException.class)
    public void userLibraryNumberNotOkTest() {
        User user = new User();
        String libraryNumber = "xxxxxxxx";
        user.setLibraryNumber(libraryNumber);
    }

    @Test
    public void userPasswordOkTest() {
        User user = new User();
        String password = "testPassword";
        user.setPassword(password);
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    public void userBookCheckedOut() {
        User user = new User();
        Book book = getBook();
        user.setBookCheckedOut(book);
        assertThat(user.getBookCheckedOut()).isEqualToComparingFieldByFieldRecursively(book);
    }

    private Book getBook() {
        Book bookCheckOut = new Book();
        bookCheckOut.setTitle("The Hobbit");
        bookCheckOut.setAuthor("J.R.R Tolkien");
        bookCheckOut.setYear(1937);
        return bookCheckOut;
    }


}