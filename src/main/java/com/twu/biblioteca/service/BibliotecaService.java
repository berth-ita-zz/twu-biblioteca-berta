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
        return "1 - List books\n2 - Check out book\n3 - Quit\n";
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

    public String printBooksList() {
        List<Book> bookList = bookRepository.getBookList();
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

}
