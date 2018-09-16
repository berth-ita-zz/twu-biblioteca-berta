package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private static final String CHECK_OUT_OPTION = "2";
    private static final String RETURN_OPTION = "3";
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getBooksList() {
        List<Book> bookList = bookRepository.getBookList();
        String bookListToPrint = "";
        for (Book book : bookList) {
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
        for (int i = 0; i < bookList.size(); i++) {
            bookListToPrint = bookListToPrint.concat(String.format("%-2s. %-45s %-20s %-4s\n", i + 1, bookList.get(i).getTitle(),
                    bookList.get(i).getAuthor(), bookList.get(i).getYearPublished().toString()));
        }
        return bookListToPrint;
    }

    public String operationBook(String bookNumber, String option) {
        if (option.equals(CHECK_OUT_OPTION)) {
            if (bookRepository.deleteBookFromList(bookNumber)) {
                return "Thank you! Enjoy the book";
            }
            return "That book is not available";
        }
        if (bookRepository.returnBookFromList(bookNumber)) {
            return "Thank you for returning the book";
        }
        return "This is not a valid book to return";
    }
}
