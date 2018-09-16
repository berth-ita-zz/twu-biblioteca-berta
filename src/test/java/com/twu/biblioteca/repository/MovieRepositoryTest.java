package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class MovieRepositoryTest {

    @Test
    public void getMovieListOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        List<Movie> movieList = movieRepository.getMovieList();
        assertThat(movieList).hasSize(5);
    }

    @Test
    public void deleteMovieFromListListSizeOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        List<Movie> movieList = movieRepository.getMovieList();
        movieRepository.deleteMovieFromList("2");
        assertThat(movieList).hasSize(4);
    }

    @Test
    public void deleteMovieFromListOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteMovieFromList("2");
        assertThat(movieRemoved).isTrue();
    }

    @Test
    public void deleteMovieFromListNotCorrectNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteMovieFromList("25");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteMovieFromListNotNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteMovieFromList("a");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListLimitNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteMovieFromList("6");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListZeroNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteMovieFromList("0");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteBookFromListNegativeNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteMovieFromList("-5");
        assertThat(movieRemoved).isFalse();
    }

}