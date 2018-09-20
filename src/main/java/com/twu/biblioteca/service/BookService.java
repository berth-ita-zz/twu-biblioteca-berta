package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;
import static com.twu.biblioteca.util.ConstantUtils.*;

public class BookService extends BibliotecaProductService<Book> {

    public BookService(BookRepository bookRepository) {
        this.bibliotecaProductRepository = bookRepository;
    }

    @Override
    protected String getElementWithFormat(Book book) {
        return String.format(BOOK_LIST_WITHOUT_ID_REGEX, book.getTitle(), book.getAuthor(),
                book.getYearPublished().toString());
    }

    @Override
    protected String getElementWithFormatAndId(Book book) {
        return String.format(BOOK_LIST_WITH_ID_REGEX, book.getId(),
                book.getTitle(), book.getAuthor(), book.getYearPublished().toString());
    }

    @Override
    protected String getCheckOutOption() {
        return BOOK_OPTION;
    }

    @Override
    protected String getElementName() {
        return BOOK_TXT;
    }

}
