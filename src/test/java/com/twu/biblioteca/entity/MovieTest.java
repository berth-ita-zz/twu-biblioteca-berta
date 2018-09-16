package com.twu.biblioteca.entity;

import com.twu.biblioteca.exception.InvalidRatingException;
import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class MovieTest {
    @Test
    public void movieTitleOkTest() {
        Movie movie = new Movie();
        String movieTitle = "Test Movie Title";
        movie.setTitle(movieTitle);
        assertThat(movie.getTitle()).isEqualTo(movieTitle);
    }

    @Test
    public void movieAuthorOkTest() {
        Movie movie = new Movie();
        String movieAuthor = "Test Movie Author";
        movie.setAuthor(movieAuthor);
        assertThat(movie.getAuthor()).isEqualTo(movieAuthor);
    }

    @Test
    public void movieYearOkTest() {
        Movie movie = new Movie();
        Integer movieYear = 1987;
        movie.setYear(movieYear);
        assertThat(movie.getYear()).isEqualTo(movieYear);
    }

    @Test(expected = InvalidRatingException.class)
    public void movieRatingMinorZeroTest() {
        Movie movie = new Movie();
        Integer movieRating = -1;
        movie.setRating(movieRating);
    }

    @Test
    public void movieRatingNumberOkTest() {
        Movie movie = new Movie();
        Integer movieRating = 5;
        movie.setRating(movieRating);
        assertThat(movie.getRating()).isEqualTo(5);
    }

    @Test
    public void movieRatingNullOkTest() {
        Movie movie = new Movie();
        assertThat(movie.getRating()).isNull();
    }

    @Test(expected = InvalidRatingException.class)
    public void movieRatingMajorTenTest() {
        Movie movie = new Movie();
        Integer movieRating = 11;
        movie.setRating(movieRating);
    }

}