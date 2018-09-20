package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import static com.twu.biblioteca.util.ConstantUtils.*;

public class MovieService extends BibliotecaProductService<Movie> {

    public MovieService(MovieRepository movieRepository) {
        this.bibliotecaProductRepository = movieRepository;
    }

    @Override
    protected String getElementWithFormat(Movie movie) {
        return String.format(MOVIE_LIST_WITHOUT_ID_REGEX, movie.getTitle(), movie.getYearPublished().toString(), movie.getAuthor(),
                movie.getRating().toString());
    }

    @Override
    protected String getElementWithFormatAndId(Movie movie) {
        return String.format(MOVIE_LIST_WITH_ID_REGEX, movie.getId(), movie.getTitle(),
                movie.getYearPublished().toString(), movie.getAuthor(), movie.getRating().toString());
    }

    @Override
    protected String getCheckOutOption() {
        return MOVIE_OPTION;
    }

    @Override
    protected String getElementName() {
        return MOVIE_TXT;
    }
}
