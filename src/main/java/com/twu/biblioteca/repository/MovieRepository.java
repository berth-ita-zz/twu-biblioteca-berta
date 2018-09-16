package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private List<Movie> moviesList = new ArrayList<>();

    public MovieRepository() {
        moviesList.add(getMovie("Beetlejuice", 1988, "Tim Burton", 7));
        moviesList.add(getMovie("The dark crystal", 1982, "Jim Henson", 7));
        moviesList.add(getMovie("Howl's Moving Castle", 2004, "Hayao Miyazak", 8));
        moviesList.add(getMovie("300", 2007, "Zack Snyder", 8));
        moviesList.add(getMovie("Labyrinth", 1986, "Jim Henson", 8));
    }

    private Movie getMovie(String title, Integer year, String author, Integer rating) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(year);
        movie.setAuthor(author);
        movie.setRating(rating);
        return movie;
    }

    public List<Movie> getMovieList() {
        return moviesList;
    }

    public Boolean deleteMovieFromList(String movieNumber) {
        if (checkMovieNumberIsNumeric(movieNumber)) return false;
        if (checkMovieNumberIsOnList(movieNumber, moviesList)) return false;
        moviesList.remove(Integer.parseInt(movieNumber) - 1);
        return true;
    }

    private boolean checkMovieNumberIsOnList(String movieNumber, List<Movie> moviesList) {
        return (Integer.parseInt(movieNumber) <= 0) || (Integer.parseInt(movieNumber) > moviesList.size());
    }

    private boolean checkMovieNumberIsNumeric(String movieNumber) {
        return (!movieNumberIsNumeric(movieNumber));
    }

    private static boolean movieNumberIsNumeric(String bookNumber) {
        return bookNumber.matches("-?\\d+(\\.\\d+)?");
    }

}
