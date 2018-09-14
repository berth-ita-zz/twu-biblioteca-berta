package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> bookList = new ArrayList<>();
    private List<Book> returnBookList = new ArrayList<>();

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

    public List<Book> getReturnBookList() {
        return returnBookList;
    }

    private Book getBook(String title, String author, Integer yearPublished) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublished(yearPublished);
        return book;
    }

    public Boolean deleteBookFromList(String bookNumber) {
        if (checkBookNumberIsNumeric(bookNumber)) return false;
        if (checkBookNumberIsOnList(bookNumber, bookList)) return false;
        returnBookList.add(bookList.get(Integer.parseInt(bookNumber) - 1));
        bookList.remove(Integer.parseInt(bookNumber) - 1);
        return true;
    }

    public Boolean returnBookFromList(String bookNumber) {
        if (checkBookNumberIsNumeric(bookNumber)) return false;
        if (checkBookNumberIsOnList(bookNumber, returnBookList)) return false;
        bookList.add(returnBookList.get(Integer.parseInt(bookNumber) - 1));
        returnBookList.remove(Integer.parseInt(bookNumber) - 1);
        return true;
    }

    private boolean checkBookNumberIsOnList(String bookNumber, List<Book> bookList) {
        if ((Integer.parseInt(bookNumber) <= 0) || (Integer.parseInt(bookNumber) > bookList.size())) {
            return true;
        }
        return false;
    }

    private boolean checkBookNumberIsNumeric(String bookNumber) {
        if ((!bookNumberIsNumeric(bookNumber))) {
            return true;
        }
        return false;
    }

    private static boolean bookNumberIsNumeric(String bookNumber) {
        return bookNumber.matches("-?\\d+(\\.\\d+)?");
    }
}
