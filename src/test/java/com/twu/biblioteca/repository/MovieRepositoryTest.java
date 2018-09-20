package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class MovieRepositoryTest {

    @Test
    public void checkOutMovieSuccessfully() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieCheckedOut = movieRepository.loggedUserCheckOutElement("1234", new User());
        assertThat(movieCheckedOut).isTrue();
    }

    @Test
    public void checkOutMovieWithIncorrectId() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieCheckedOut = movieRepository.loggedUserCheckOutElement("a", new User());
        assertThat(movieCheckedOut).isFalse();
    }

    @Test
    public void checkOutMovieWhenUserAlreadyCheckedOutOne() {
        MovieRepository movieRepository = new MovieRepository();
        User user = new User();
        user.setMovieCheckedOut(new Movie());
        Boolean movieCheckedOut = movieRepository.loggedUserCheckOutElement("1", user);
        assertThat(movieCheckedOut).isFalse();

    }

    @Test
    public void userReturnMovieSuccessfully() {
        MovieRepository movieRepository = new MovieRepository();
        User user = new User();
        movieRepository.loggedUserCheckOutElement("1234", user);
        Boolean movieCheckedOut = movieRepository.loggedUserReturnElement(user);
        assertThat(movieCheckedOut).isTrue();
    }

    @Test
    public void userReturnMovieIncorrect() {
        MovieRepository movieRepository = new MovieRepository();
        User user = new User();
        Boolean movieCheckedOut = movieRepository.loggedUserReturnElement(user);
        assertThat(movieCheckedOut).isFalse();
    }

}
