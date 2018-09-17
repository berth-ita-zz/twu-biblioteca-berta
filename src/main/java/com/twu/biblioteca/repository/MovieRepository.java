package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;

public class MovieRepository extends BibliotecaProductRepository<Movie> {

    public MovieRepository() {
        availableList.add(getMovie("1234", "Beetlejuice", 1988, "Tim Burton", 7));
        availableList.add(getMovie("0034", "The dark crystal", 1982, "Jim Henson", 7));
        availableList.add(getMovie("9756", "Howl's Moving Castle", 2004, "Hayao Miyazak", 8));
        availableList.add(getMovie("7800", "300", 2007, "Zack Snyder", 8));
        availableList.add(getMovie("0147", "Labyrinth", 1986, "Jim Henson", 8));
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

    @Override
    protected Movie getCheckedOutElement(User user) {
        return user.getMovieCheckedOut();
    }

    @Override
    protected void setElementCheckedOut(User user, Movie movie) {
        user.setMovieCheckedOut(movie);
    }
}
