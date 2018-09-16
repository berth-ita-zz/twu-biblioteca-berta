package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Test
    public void selectMenuOptionListBookOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        List<Movie> movieListMocked = getMoviesList();
        Mockito.when(movieRepository.getMovieList()).thenReturn(movieListMocked);
        String movieList = movieService.getMoviesList();
        assertThat(movieList).isEqualTo(String.format("%-30s %-4s %-15s %-1s\n", "Beetlejuice", 1988, "Tim Burton", 7)
                + String.format("%-30s %-4s %-15s %-1s\n", "The dark crystal", 1982, "Jim Henson", 7));
    }

    @Test
    public void selectMenuOptionCheckOutBookOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        List<Movie> movieListMocked = getMoviesList();
        Mockito.when(movieRepository.getMovieList()).thenReturn(movieListMocked);
        String movieList = movieService.printMoviesList();
        assertThat(movieList).isEqualTo(String.format("%-2s. %-30s %-4s %-15s %-1s\n", "1", "Beetlejuice", 1988, "Tim Burton", 7)
                + String.format("%-2s. %-30s %-4s %-15s %-1s\n","2","The dark crystal", 1982, "Jim Henson", 7));
    }

    @Test
    public void checkOutMovieOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        Mockito.when(movieRepository.deleteMovieFromList("2")).thenReturn(true);
        String movieRemoved = movieService.operationMovie("2");
        Mockito.verify(movieRepository).deleteMovieFromList("2");
        assertThat(movieRemoved).isEqualTo("Thank you! Enjoy the movie");
    }

    @Test
    public void checkOutMovieNotOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        Mockito.when(movieRepository.deleteMovieFromList("25")).thenReturn(false);
        String movieRemoved = movieService.operationMovie("25");
        Mockito.verify(movieRepository).deleteMovieFromList("25");
        assertThat(movieRemoved).isEqualTo("That movie is not available");
    }

    private List<Movie> getMoviesList() {
        List<Movie> movieListMocked = new ArrayList<>();
        movieListMocked.add(getMovie("Beetlejuice", 1988, "Tim Burton", 7));
        movieListMocked.add(getMovie("The dark crystal", 1982, "Jim Henson", 7));
        return movieListMocked;
    }

    private Movie getMovie(String title, Integer year, String author, Integer rating) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(year);
        movie.setAuthor(author);
        movie.setRating(rating);
        return movie;
    }

}