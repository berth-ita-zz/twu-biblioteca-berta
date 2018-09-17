package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;
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
        movieRepository.deleteFromList("2");
        assertThat(movieList).hasSize(4);
    }

    @Test
    public void deleteMovieFromListOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteFromList("2");
        assertThat(movieRemoved).isTrue();
    }

    @Test
    public void deleteMovieFromListNotCorrectNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteFromList("25");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteMovieFromListNotNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteFromList("a");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteMovieFromListLimitNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteFromList("6");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void deleteMovieFromListZeroNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        Boolean movieRemoved = movieRepository.deleteFromList("0");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void returnMovieListSizeOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        List<Movie> returnMovieList = movieRepository.getReturnList();
        movieRepository.deleteFromList("2");
        assertThat(returnMovieList).hasSize(1);
    }

    @Test
    public void returnMovieOkTest() {
        MovieRepository movieRepository = new MovieRepository();
        movieRepository.deleteFromList("2");
        Boolean movieRemoved = movieRepository.returnFromList("1");
        assertThat(movieRemoved).isTrue();
    }

    @Test
    public void returnMovieNotCorrectNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        movieRepository.deleteFromList("2");
        Boolean movieRemoved = movieRepository.returnFromList("7");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void returnMovieNotNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        movieRepository.deleteFromList("1");
        Boolean movieRemoved = movieRepository.returnFromList("a");
        assertThat(movieRemoved).isFalse();
    }

    @Test
    public void returnMovieZeroNumberTest() {
        MovieRepository movieRepository = new MovieRepository();
        movieRepository.deleteFromList("1");
        Boolean movieRemoved = movieRepository.returnFromList("0");
        assertThat(movieRemoved).isFalse();
    }

}