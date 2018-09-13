package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> bookList = new ArrayList<>();

    public BookRepository() {
        bookList.add(getBook("The Hobbit"));
        bookList.add(getBook("The Hitchhiker's Guide to the Galaxy"));
        bookList.add(getBook("The thief of Time"));
        bookList.add(getBook("Hogsfather"));
        bookList.add(getBook("Harry Potter and the Prisoner of Azkaban"));
    }

    public List<Book> getBookList() {
        return bookList;
    }

    private Book getBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        return book;
    }

}
