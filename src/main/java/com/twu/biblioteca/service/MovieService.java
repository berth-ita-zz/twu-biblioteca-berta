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

    public String printMoviesList() {
        String movieListToPrint = "";
        List<Movie> movieList = movieRepository.getMovieList();
        for (int i = 0; i < movieList.size(); i++) {
             movieListToPrint = movieListToPrint.concat(String.format("%-2s. %-30s %-4s %-15s %-1s\n", i + 1, movieList.get(i).getTitle(),
                     movieList.get(i).getYear().toString(), movieList.get(i).getAuthor(), movieList.get(i).getRating().toString()));
        }
        return movieListToPrint;
    }

    public String operationMovie(String movieNumber) {
        if (movieRepository.deleteMovieFromList(movieNumber)) {
            return "Thank you! Enjoy the movie";
        }
        return "That movie is not available";
    }

}
