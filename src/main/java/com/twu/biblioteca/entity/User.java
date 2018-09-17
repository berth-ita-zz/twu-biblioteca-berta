package com.twu.biblioteca.entity;

import com.twu.biblioteca.exception.InvalidLibraryNumberException;

public class User {

    private String libraryNumber;
    private String password;
    private Book bookCheckedOut;
    private Movie movieCheckedOut;

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

    public Movie getMovieCheckedOut() {
        return movieCheckedOut;
    }

    public void setMovieCheckedOut(Movie movieCheckedOut) {
        this.movieCheckedOut = movieCheckedOut;
    }
}
