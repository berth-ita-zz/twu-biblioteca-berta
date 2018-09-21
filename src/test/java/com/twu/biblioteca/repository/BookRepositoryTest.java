package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.util.FileReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BookRepositoryTest {

    @Mock
    private FileReader fileReader;

    @Test
    public void checkOutBookSuccessfully() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("0001;The Hobbit;J.R.R Tolkien;1937").thenReturn(null);
        BookRepository bookRepository = new BookRepository(fileReader);
        Boolean bookCheckedOut = bookRepository.loggedUserCheckOutElement("0001", new User());
        assertThat(bookCheckedOut).isTrue();
    }

    @Test
    public void checkOutBookWithIncorrectId() throws Exception {
        BookRepository bookRepository = new BookRepository(fileReader);
        Boolean bookCheckedOut = bookRepository.loggedUserCheckOutElement("a", new User());
        assertThat(bookCheckedOut).isFalse();
    }

    @Test
    public void checkOutBookWhenUserAlreadyCheckedOutOne() throws Exception {
        BookRepository bookRepository = new BookRepository(fileReader);
        User user = new User();
        user.setBookCheckedOut(new Book());
        Boolean bookCheckedOut = bookRepository.loggedUserCheckOutElement("1", user);
        assertThat(bookCheckedOut).isFalse();
    }

    @Test
    public void userReturnBookSuccessfully() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("0001;The Hobbit;J.R.R Tolkien;1937").thenReturn(null);
        BookRepository bookRepository = new BookRepository(fileReader);
        User user = new User();
        bookRepository.loggedUserCheckOutElement("0001", user);
        Boolean bookCheckedOut = bookRepository.loggedUserReturnElement(user);
        assertThat(bookCheckedOut).isTrue();
    }

    @Test
    public void userReturnBookIncorrect() throws Exception {
        BookRepository bookRepository = new BookRepository(fileReader);
        User user = new User();
        Boolean bookCheckedOut = bookRepository.loggedUserReturnElement(user);
        assertThat(bookCheckedOut).isFalse();
    }

}
