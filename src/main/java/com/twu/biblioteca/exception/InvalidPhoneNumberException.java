package com.twu.biblioteca.exception;

public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException() {
        super("Invalid user Phone Number");
    }

}
