package com.twu.biblioteca.exception;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("Invalid user email");
    }

}
