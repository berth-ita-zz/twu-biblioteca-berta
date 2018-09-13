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

    public String getMainMenuMessage() {
        return "1 - List books";
    }

    public String selectMenuOption(String option) {
        switch (option) {
            case "1":
                String bookList = getBooksList();
                return bookList;
            default:
                return "Select a valid option!";
        }
    }

    private String getBooksList() {
        List<Book> bookList = bookRepository.getBookList();
        String bookListToPrint = "";
        for (Book book: bookList) {
            bookListToPrint = bookListToPrint.concat(String.format("%-45s %-30s %-4s\n", book.getTitle(), book.getAuthor(), book.getYearPublished().toString()));
        }

        return bookListToPrint;
    }


}
