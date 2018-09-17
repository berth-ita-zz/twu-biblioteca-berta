package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class MovieRepositoryTest {

    @Test
    public void getMovieListOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        List<Movie> movieList = movieRepository.getList();
        assertThat(movieList).hasSize(5);
    }

    @Test
    public void deleteMovieFromListListSizeOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        List<Movie> movieList = movieRepository.getList();
        movieRepository.loggedUserCheckOutElement("2", new User());
        assertThat(movieList).hasSize(4);
    }

    @Test
    public void deleteMovieFromListOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.loggedUserCheckOutElement("2", new User());
        assertThat(movieRemoved).isTrue();
    }

    @Test
    public void deleteMovieFromListNotNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.loggedUserCheckOutElement("a", new User());
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteMovieFromListLimitNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.loggedUserCheckOutElement("6", new User());
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteMovieFromListZeroNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.loggedUserCheckOutElement("0", new User());
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteMovieFromListUserWithMovie() {
        MovieRepository movieRepository = new MovieRepository();
        User user = new User();
        user.setMovieCheckedOut(new Movie());
        Boolean movieRemoved = movieRepository.loggedUserCheckOutElement("1", user);
        assertThat(movieRemoved).isFalse();

    }

    @Test
    public void returnMovieOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        User user = new User();
        movieRepository.loggedUserCheckOutElement("2", user);
        Boolean movieRemoved = movieRepository.loggedUserReturnElement(user);
        assertThat(movieRemoved).isTrue();
    }

    @Test
    public void returnMovieNotOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        User user = new User();
        Boolean movieRemoved = movieRepository.loggedUserReturnElement(user);
        assertThat(movieRemoved).isFalse();
    }

}
