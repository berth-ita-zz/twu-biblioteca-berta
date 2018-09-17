package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;

public class BookService extends BibliotecaProductService<Book> {

    public BookService(BookRepository bookRepository) {
        this.bibliotecaProductRepository = bookRepository;
    }

    @Override
    protected String getElementWithFormat(Book book) {
        return String.format("%-45s %-20s %-4s\n", book.getTitle(), book.getAuthor(),
                book.getYearPublished().toString());
    }

    @Override
    protected String getElementWithFormatAndId(Book book) {
        return String.format("%-4s %-45s %-20s %-4s\n", book.getId(),
                book.getTitle(), book.getAuthor(), book.getYearPublished().toString());
    }

    @Override
    protected String getCheckOutOption() {
        return "2";
    }

    @Override
    protected String getElementName() {
        return "book";
    }

}
