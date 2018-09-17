package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Test
    public void selectMenuOptionListMovieOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        List<Movie> movieListMocked = getMoviesList();
        Mockito.when(movieRepository.getList()).thenReturn(movieListMocked);
        String movieList = movieService.getList();
        assertThat(movieList).isEqualTo(String.format("%-30s %-4s %-15s %-1s\n", "Beetlejuice", 1988, "Tim Burton", 7)
                + String.format("%-30s %-4s %-15s %-1s\n", "The dark crystal", 1982, "Jim Henson", 7));
    }

    @Test
    public void selectMenuOptionCheckOutMovieOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        List<Movie> movieListMocked = getMoviesList();
        Mockito.when(movieRepository.getList()).thenReturn(movieListMocked);
        String movieList = movieService.printList("5");
        assertThat(movieList).isEqualTo(String.format("%-4s %-30s %-4s %-15s %-1s\n", "1234", "Beetlejuice", 1988, "Tim Burton", 7)
                + String.format("%-4s %-30s %-4s %-15s %-1s\n","0034","The dark crystal", 1982, "Jim Henson", 7));
    }

    @Test
    public void checkOutMovieOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        Mockito.when(movieRepository.deleteFromList("5")).thenReturn(true);
        String movieRemoved = movieService.elementOperation("5", "5");
        Mockito.verify(movieRepository).deleteFromList("5");
        assertThat(movieRemoved).isEqualTo("Thank you! Enjoy the movie");
    }

    @Test
    public void checkOutMovieNotOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        Mockito.when(movieRepository.deleteFromList("25")).thenReturn(false);
        String movieRemoved = movieService.elementOperation("25", "5");
        Mockito.verify(movieRepository).deleteFromList("25");
        assertThat(movieRemoved).isEqualTo("That movie is not available");
    }

    @Test
    public void selectMenuOptionReturnMovieOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        List<Movie> movieListMocked = getMoviesList();
        Mockito.when(movieRepository.getReturnList()).thenReturn(movieListMocked);
        String movieList = movieService.printList("6");
        assertThat(movieList).isEqualTo(String.format("%-4s %-30s %-4s %-15s %-1s\n", "1234", "Beetlejuice", 1988, "Tim Burton", 7)
                + String.format("%-4s %-30s %-4s %-15s %-1s\n","0034","The dark crystal", 1982, "Jim Henson", 7));
    }

    @Test
    public void returnMovieOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        Mockito.when(movieRepository.returnFromList("1")).thenReturn(true);
        String movieReturned = movieService.elementOperation("1", "6");
        Mockito.verify(movieRepository).returnFromList("1");
        assertThat(movieReturned).isEqualTo("Thank you for returning the movie");
    }

    @Test
    public void returnMovieNotOkTest() {
        MovieService movieService = new MovieService(movieRepository);
        Mockito.when(movieRepository.returnFromList("15")).thenReturn(false);
        String movieReturned = movieService.elementOperation("15", "6");
        Mockito.verify(movieRepository).returnFromList("15");
        assertThat(movieReturned).isEqualTo("This is not a valid movie to return");
    }

    private List<Movie> getMoviesList() {
        List<Movie> movieListMocked = new ArrayList<>();
        movieListMocked.add(getMovie("1234","Beetlejuice", 1988, "Tim Burton", 7));
        movieListMocked.add(getMovie("0034", "The dark crystal", 1982, "Jim Henson", 7));
        return movieListMocked;
    }

    private Movie getMovie(String id, String title, Integer year, String author, Integer rating) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setYearPublished(year);
        movie.setAuthor(author);
        movie.setRating(rating);
        return movie;
    }

}