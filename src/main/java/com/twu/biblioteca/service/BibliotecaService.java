package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;

public class BibliotecaService {

    private BookService bookService;
    private MovieService movieService;

    public BibliotecaService(BookService bookService, MovieService movieService) {
        this.bookService = bookService;
        this.movieService = movieService;
    }

    public String getWelcomeMessage() {
        return "Welcome to La Biblioteca!";
    }

    public String getMainMenuMessage() {
        return "1 - List books\n2 - Check out book\n3 - Return book\n4 - List movies\n5 - Quit\n";
    }

    public String getBooksList() {
        return bookService.getBooksList();
    }

    public String printBooksList(){
        return bookService.printBooksList();
    }

    public String operationBook(String bookNumber, String option, User user){
        return bookService.operationBook(bookNumber, option, user);
    }

    public String getMoviesList() {
        return movieService.getMoviesList();
    }

    public String printMoviesList() {
        return movieService.printMoviesList();
    }

    public String operationMovie(String movieNumber) {
        return movieService.operationMovie(movieNumber);
    }

}
