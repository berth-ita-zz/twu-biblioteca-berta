package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> bookList = new ArrayList<>();

    public BookRepository() {
        bookList.add(getBook("The Hobbit", "J.R.R Tolkien", 1937));
        bookList.add(getBook("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
        bookList.add(getBook("The thief of Time", "Terry Pratchett", 2001));
        bookList.add(getBook("Hogsfather", "Terry Pratchett", 1996));
        bookList.add(getBook("Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999));
    }

    public List<Book> getBookList() {
        return bookList;
    }

    private Book getBook(String title, String author, Integer yearPublished) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublished(yearPublished);
        return book;
    }

    public Boolean deleteBookFromList(String bookNumber) {
        if((!bookNumberIsNumeric(bookNumber))) {
            return false;
        }
        if((Integer.parseInt(bookNumber) <= 0) || (Integer.parseInt(bookNumber) > bookList.size())) {
            return false;
        }
        bookList.remove(Integer.parseInt(bookNumber) - 1);
        return true;
    }

    private static boolean bookNumberIsNumeric(String bookNumber)
    {
        return bookNumber.matches("-?\\d+(\\.\\d+)?");
    }
}
