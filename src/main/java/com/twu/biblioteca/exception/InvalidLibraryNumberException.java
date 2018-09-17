package com.twu.biblioteca.exception;

public class InvalidLibraryNumberException extends RuntimeException {

    public InvalidLibraryNumberException() {
        super("Invalid user Library Number");
    }

}
