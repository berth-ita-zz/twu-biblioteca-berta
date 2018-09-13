package com.twu.biblioteca.entity;

import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BookTest {

    @Test
    public void bookTitleOkTest() {
        Book book = new Book();
        String bookTitle = "Test Book Title";
        book.setTitle(bookTitle);
        assertThat(book.getTitle()).isEqualTo(bookTitle);
    }
}