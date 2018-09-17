package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;

public class BookRepository extends BibliotecaProductRepository<Book> {

    public BookRepository() {
        availableList.add(getBook("0001","The Hobbit", "J.R.R Tolkien", 1937));
        availableList.add(getBook("0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
        availableList.add(getBook("0012","The thief of Time", "Terry Pratchett", 2001));
        availableList.add(getBook("1021","Hogsfather", "Terry Pratchett", 1996));
        availableList.add(getBook("0987","Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999));
    }

    private Book getBook(String id, String title, String author, Integer yearPublished) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublished(yearPublished);
        return book;
    }

    @Override
    protected Book getCheckedOutElement(User user) {
        return user.getBookCheckedOut();
    }

    @Override
    protected void setElementCheckedOut(User user, Book book) {
        user.setBookCheckedOut(book);
    }
}
