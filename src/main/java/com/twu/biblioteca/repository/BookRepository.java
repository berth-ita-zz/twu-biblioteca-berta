package com.twu.biblioteca.repository;

import com.sun.istack.internal.NotNull;
import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.util.NumericUtils;

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
        book.setYear(yearPublished);
        return book;
    }

    public Boolean loggedUserCheckOutBook(String bookNumber, @NotNull User user) {
        if(user.getBookCheckedOut() == null) {
            if (verifyBookNumber(bookNumber, bookList)) return false;
            user.setBookCheckedOut(bookList.get(Integer.parseInt(bookNumber) - 1));
            bookList.remove(Integer.parseInt(bookNumber) - 1);
            return true;
        }
        return false;
    }

    public Boolean loggedUserReturnBook(@NotNull User user) {
        if(user.getBookCheckedOut() != null) {
            bookList.add(user.getBookCheckedOut());
            user.setBookCheckedOut(null);
            return true;
        }
        return false;
    }

    private boolean verifyBookNumber(String bookNumber, List<Book> listToDelete) {
        if (!NumericUtils.numberIsNumeric(bookNumber)) return true;
        if (NumericUtils.checkElementIsOnList(bookNumber, listToDelete.size())) return true;
        return false;
    }

}
