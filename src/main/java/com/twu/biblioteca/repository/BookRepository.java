package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> bookList = new ArrayList<>();
    private List<Book> returnBookList = new ArrayList<>();

    public BookRepository() {
        bookList.add(getBook("0001","The Hobbit", "J.R.R Tolkien", 1937));
        bookList.add(getBook("0002","The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979));
        bookList.add(getBook("0012","The thief of Time", "Terry Pratchett", 2001));
        bookList.add(getBook("1021","Hogsfather", "Terry Pratchett", 1996));
        bookList.add(getBook("0987","Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999));
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public List<Book> getReturnBookList() {
        return returnBookList;
    }

    private Book getBook(String id, String title, String author, Integer yearPublished) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYearPublished(yearPublished);
        return book;
    }

    public Boolean deleteBookFromList(String bookNumber) {
        return getListOperationResult(bookNumber, bookList, returnBookList);
    }

    public Boolean returnBookFromList(String bookNumber) {
        return getListOperationResult(bookNumber, returnBookList, bookList);
    }

    private Boolean getListOperationResult(String bookNumber, List<Book> listToDelete, List<Book> listToAdd) {
        for (int i = 0; i < listToDelete.size(); i++) {
            if(listToDelete.get(i).getId().equals(bookNumber)){
                listToAdd.add(listToDelete.get(i));
                listToDelete.remove(i);
                return true;
            }
        }
        return false;
    }
}
