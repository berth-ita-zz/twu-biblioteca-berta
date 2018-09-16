package com.twu.biblioteca.service;

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

    public String printBooksList(String option){
        return bookService.printBooksList(option);
    }

    public String operationBook(String bookNumber, String option){
        return bookService.operationBook(bookNumber, option);
    }

    public String getMoviesList() {
        return movieService.getMoviesList();
    }

}
