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
    public void bookYearOkTest() {
        Book book = new Book();
        Integer bookYear = 1987;
        book.setYear(bookYear);
        assertThat(book.getYear()).isEqualTo(bookYear);
    }

    @Test
    public void compareBooksOkTest() {
        Book book1 = createBook();
        Book book2 = createBook();
        assertThat(book1.equals(book2)).isTrue();
    }

    private Book createBook() {
        Book book = new Book();
        book.setTitle("Test Book Title");
        book.setAuthor("Test Book Author");
        book.setYear(1987);
        return book;
    }
}