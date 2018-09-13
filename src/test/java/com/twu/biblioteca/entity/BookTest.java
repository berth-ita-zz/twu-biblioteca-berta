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

    @Test
    public void bookAuthorOkTest() {
        Book book = new Book();
        String bookAuthor = "Test Book Author";
        book.setAuthor(bookAuthor);
        assertThat(book.getAuthor()).isEqualTo(bookAuthor);
    }

    @Test
    public void bookYearPublishedOkTest() {
        Book book = new Book();
        Integer bookYearPublished = 1987;
        book.setYearPublished(bookYearPublished);
        assertThat(book.getYearPublished()).isEqualTo(bookYearPublished);
    }
}