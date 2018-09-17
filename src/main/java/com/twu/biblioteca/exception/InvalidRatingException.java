package com.twu.biblioteca.exception;

public class InvalidRatingException extends RuntimeException {

    public InvalidRatingException() {
        super("Invalid Rating number");
    }
}