package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.repository.MovieRepository;

public class MovieService extends BibliotecaProductService<Movie> {

    public MovieService(MovieRepository movieRepository) {
        this.bibliotecaProductRepository = movieRepository;
    }

    @Override
    protected String getElementWithFormat(Movie movie) {
        return String.format("%-30s %-4s %-15s %-1s\n", movie.getTitle(), movie.getYearPublished().toString(), movie.getAuthor(),
                movie.getRating().toString());
    }

    @Override
    protected String getElementWithFormatAndId(Movie movie) {
        return String.format("%-4s %-30s %-4s %-15s %-1s\n", movie.getId(), movie.getTitle(),
                movie.getYearPublished().toString(), movie.getAuthor(), movie.getRating().toString());
    }

    @Override
    protected String getCheckOutOption() {
        return "5";
    }

    @Override
    protected String getReturnOption() {
        return "6";
    }

    @Override
    protected String getElementName() {
        return "movie";
    }
}
