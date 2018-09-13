package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BookRepositoryTest {

    @Test
    public void getBookListOkTest() {
        BookRepository bookRepository = new BookRepository();
        List<Book> bookList = bookRepository.getBookList();
        assertThat(bookList).hasSize(5);
    }

}