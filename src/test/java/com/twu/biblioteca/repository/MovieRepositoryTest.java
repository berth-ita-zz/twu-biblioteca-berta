package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.util.FileReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryTest {

    @Mock
    private FileReader fileReader;

    @Test
    public void checkOutMovieSuccessfully() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("1234;Beetlejuice;1988;Tim Burton;7").thenReturn(null);
        MovieRepository movieRepository = new MovieRepository(fileReader);
        Boolean movieCheckedOut = movieRepository.loggedUserCheckOutElement("1234", new User());
        assertThat(movieCheckedOut).isTrue();
    }

    @Test
    public void checkOutMovieWithIncorrectId() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("1234;Beetlejuice;1988;Tim Burton;7").thenReturn(null);
        MovieRepository movieRepository = new MovieRepository(fileReader);
        Boolean movieCheckedOut = movieRepository.loggedUserCheckOutElement("a", new User());
        assertThat(movieCheckedOut).isFalse();
    }

    @Test
    public void checkOutMovieWhenUserAlreadyCheckedOutOne() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("1234;Beetlejuice;1988;Tim Burton;7").thenReturn(null);
        MovieRepository movieRepository = new MovieRepository(fileReader);
        User user = new User();
        user.setMovieCheckedOut(new Movie());
        Boolean movieCheckedOut = movieRepository.loggedUserCheckOutElement("1", user);
        assertThat(movieCheckedOut).isFalse();

    }

    @Test
    public void userReturnMovieSuccessfully() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("1234;Beetlejuice;1988;Tim Burton;7").thenReturn(null);
        MovieRepository movieRepository = new MovieRepository(fileReader);
        User user = new User();
        movieRepository.loggedUserCheckOutElement("1234", user);
        Boolean movieCheckedOut = movieRepository.loggedUserReturnElement(user);
        assertThat(movieCheckedOut).isTrue();
    }

    @Test
    public void userReturnMovieIncorrect() throws Exception {
        Mockito.when(fileReader.readLine()).thenReturn("1234;Beetlejuice;1988;Tim Burton;7").thenReturn(null);
        MovieRepository movieRepository = new MovieRepository(fileReader);
        User user = new User();
        Boolean movieCheckedOut = movieRepository.loggedUserReturnElement(user);
        assertThat(movieCheckedOut).isFalse();
    }

}
