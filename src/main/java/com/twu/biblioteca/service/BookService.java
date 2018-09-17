package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;

public class BookService {

    private static final String CHECK_OUT_OPTION = "2";
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getBooksList() {
        List<Book> bookList = bookRepository.getBookList();
        String bookListToPrint = "";
        for (Book book : bookList) {
            bookListToPrint = bookListToPrint.concat(String.format("%-45s %-20s %-4s\n", book.getTitle(), book.getAuthor(),
                    book.getYear().toString()));
        }
        return bookListToPrint;
    }

    public String printBooksList() {
        List<Book> bookList = bookRepository.getBookList();
        String bookListToPrint = "";
        for (int i = 0; i < bookList.size(); i++) {
            bookListToPrint = bookListToPrint.concat(String.format("%-2s. %-45s %-20s %-4s\n", i + 1, bookList.get(i).getTitle(),
                    bookList.get(i).getAuthor(), bookList.get(i).getYear().toString()));
        }
        return bookListToPrint;
    }

    public String operationBook(String bookNumber, String option, User user) {
        if (option.equals(CHECK_OUT_OPTION)) {
            if (bookRepository.loggedUserCheckOutBook(bookNumber, user)) {
                return "Thank you! Enjoy the book";
            }
            return "That book is not available";
        }
        if (bookRepository.loggedUserReturnBook(user)) {
            return "Thank you for returning the book";
        }
        return "You do not have any book to return";
    }
}
