package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    private static final String CHECK_OUT_OPTION = "2";
    private static final String RETURN_OPTION = "3";
    private BookRepository bookRepository;

    public BibliotecaService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getWelcomeMessage() {
        return "Welcome to La Biblioteca!";
    }

    public String getMainMenuMessage() {
        return "1 - List books\n2 - Check out book\n3 - Return book\n4 - Quit\n";
    }

    public String getBooksList() {
        List<Book> bookList = bookRepository.getBookList();
        String bookListToPrint = "";
        for (Book book: bookList) {
            bookListToPrint = bookListToPrint.concat(String.format("%-45s %-20s %-4s\n", book.getTitle(), book.getAuthor(),
                    book.getYearPublished().toString()));
        }
        return bookListToPrint;
    }

    public String printBooksList(String option) {
        List<Book> bookList = new ArrayList<>();
        if (option.equals(CHECK_OUT_OPTION)) {
            bookList = bookRepository.getBookList();
        }
        if (option.equals(RETURN_OPTION)) {
            bookList = bookRepository.getReturnBookList();
        }
        String bookListToPrint = "";
        for (Book aBookList : bookList) {
            bookListToPrint = bookListToPrint.concat(String.format("%-4s %-45s %-20s %-4s\n", aBookList.getId(),
                    aBookList.getTitle(), aBookList.getAuthor(), aBookList.getYearPublished().toString()));
        }
        return bookListToPrint;
    }

    public String operationBook(String bookNumber, String option) {
        if(option.equals(CHECK_OUT_OPTION)) {
            if (bookRepository.deleteBookFromList(bookNumber)) {
                return "Thank you! Enjoy the book";
            }
            return "That book is not available";
        }
        if(bookRepository.returnBookFromList(bookNumber)) {
            return "Thank you for returning the book";
        }
        return "This is not a valid book to return";
    }

}
