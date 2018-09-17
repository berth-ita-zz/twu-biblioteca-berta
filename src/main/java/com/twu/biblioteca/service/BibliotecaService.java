package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;

public class BibliotecaService {

    private BookService bookService;
    private MovieService movieService;
    private UserService userService;

    public BibliotecaService(BookService bookService, MovieService movieService, UserService userService) {
        this.bookService = bookService;
        this.movieService = movieService;
        this.userService = userService;
    }

    public String getWelcomeMessage() {
        return "Welcome to La Biblioteca!";
    }

    public String getMainMenuMessageUserNotLogged() {
        return "1 - List books\n2 - List movies\n3- Log in\n4 - Quit\n";
    }

    public String getMainMenuMessageUserLogged() {
        return "1 - List books\n2 - Check out book\n3 - Return book\n4 - List movies\n5 - Check out movie\n" +
                "6 - Return movie\n7 - Log out\n8 - Quit\n";
    }

    public String getBooksList() {
        return bookService.getList();
    }

    public String printBooksList(String option) {
        return bookService.printList(option);
    }

    public String operationBook(String bookId, String option) {
        return bookService.elementOperation(bookId, option);
    }

    public String getMoviesList() {
        return movieService.getList();
    }

    public String printMoviesList(String option) {
        return movieService.printList(option);
    }

    public String operationMovie(String movieId, String option) {
        return movieService.elementOperation(movieId, option);
    }

    public User userLogIn(String libraryNumber, String password) {
        return userService.userLogIn(libraryNumber, password);
    }

}
