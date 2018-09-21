package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.util.FileReader;

public class BookRepository extends BibliotecaProductRepository<Book> {

    public BookRepository(FileReader fileReader) throws Exception {
        String line;
        while ((line = fileReader.readLine()) != null) {
            String[] lineSplit = line.split(";");
            availableList.add(getBook(lineSplit[0], lineSplit[1], lineSplit[2], Integer.valueOf(lineSplit[3])));
        }
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
