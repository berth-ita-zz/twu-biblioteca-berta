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

}