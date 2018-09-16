package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.repository.MovieRepository;

import java.util.List;

public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String getMoviesList() {
        List<Movie> movieList = movieRepository.getMovieList();
        String movieListToPrint = "";
        for (Movie movie : movieList) {
            movieListToPrint = movieListToPrint.concat(String.format("%-30s %-4s %-15s %-1s\n", movie.getTitle(), movie.getYear().toString(), movie.getAuthor(),
                    movie.getRating().toString()));
        }
        return movieListToPrint;
    }
}
