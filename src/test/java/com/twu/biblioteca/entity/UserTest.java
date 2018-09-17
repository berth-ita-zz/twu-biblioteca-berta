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

    @Test
    public void userNameOkTest() {
        User user = new User();
        String name = "User name";
        user.setName(name);
        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    public void userEmailOkTest() {
        User user = new User();
        String email = "userEmail@email.com";
        user.setEmail(email);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void userAddressOkTest() {
        User user = new User();
        String address = "user address";
        user.setAddress(address);
        assertThat(user.getAddress()).isEqualTo(address);
    }

    @Test
    public void userPhoneNumberOkTest() {
        User user = new User();
        Integer phone = 930000000;
        user.setPhoneNumber(phone);
        assertThat(user.getPhoneNumber()).isEqualTo(phone);
    }

    private Book getBook() {
        Book bookCheckOut = new Book();
        bookCheckOut.setTitle("The Hobbit");
        bookCheckOut.setAuthor("J.R.R Tolkien");
        bookCheckOut.setYear(1937);
        return bookCheckOut;
    }

}