package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.util.FileReader;

public class MovieRepository extends BibliotecaProductRepository<Movie> {

    public MovieRepository(FileReader fileReader) throws Exception {
        String line;
        while ((line = fileReader.readLine()) != null) {
            String[] lineSplit = line.split(";");
            availableList.add(getMovie(lineSplit[0], lineSplit[1], Integer.valueOf(lineSplit[2]), lineSplit[3], Integer.valueOf(lineSplit[4])));
        }
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
