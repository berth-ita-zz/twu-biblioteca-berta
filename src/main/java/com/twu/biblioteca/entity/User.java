package com.twu.biblioteca.entity;

import com.twu.biblioteca.exception.InvalidLibraryNumberException;

import java.util.Objects;

public class User {

    private String libraryNumber;
    private String password;
    private Book bookCheckedOut;
    private String name;
    private String email;
    private String address;
    private Integer phoneNumber;

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        if(!libraryNumber.matches("[\\w\\d]{3}-[\\w\\d]{4}")) {
            throw new InvalidLibraryNumberException();
        }
        this.libraryNumber = libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Book getBookCheckedOut() {
        return bookCheckedOut;
    }

    public void setBookCheckedOut(Book bookCheckedOut) {
        this.bookCheckedOut = bookCheckedOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(libraryNumber, user.libraryNumber) &&
                Objects.equals(password, user.password) &&
                Objects.equals(bookCheckedOut, user.bookCheckedOut);
    }

}
