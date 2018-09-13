package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;

public class BibliotecaService {

    private BookRepository bookRepository;

    public BibliotecaService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getWelcomeMessage() {
        return "Welcome to La Biblioteca!";
    }

    public String getBooksList() {
        List<Book> bookList = bookRepository.getBookList();
        String bookListToPrint = "";
        for (Book book: bookList) {
            bookListToPrint = bookListToPrint.concat(book.getTitle() + "\n");
        }

        return bookListToPrint;
    }
}
