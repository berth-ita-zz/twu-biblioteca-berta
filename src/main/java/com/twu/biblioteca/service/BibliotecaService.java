package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;

import java.util.ArrayList;
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
        if (option.equals("2")) {
            bookList = bookRepository.getBookList();
        }
        if (option.equals("3")) {
            bookList = bookRepository.getReturnBookList();
        }
        String bookListToPrint = "";
        for (int i = 0; i < bookList.size(); i++) {
            bookListToPrint = bookListToPrint.concat(String.format("%-2s. %-45s %-20s %-4s\n", i + 1, bookList.get(i).getTitle(),
                    bookList.get(i).getAuthor(), bookList.get(i).getYearPublished().toString()));
        }

        return bookListToPrint;
    }

    public String checkOutBook(String bookNumber) {
        if(bookRepository.deleteBookFromList(bookNumber)) {
            return "Thank you! Enjoy the book";
        }
        return "That book is not available";
    }

    public String returnBook(String bookNumber) {
        if(bookRepository.returnBookFromList(bookNumber)) {
            return "Thank you for returning the book";
        }
        return "This is not a valid book to return";
    }
}
