package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.User;
import static com.twu.biblioteca.util.ConstantUtils.*;

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
        return WELCOME_TO_LA_BIBLIOTECA_MSG;
    }

    public String getMainMenuMessageUserNotLogged() {
        return MENU_USER_NOT_LOGGED_MSG;
    }

    public String getMainMenuMessageUserLogged() {
        return MENU_USER_LOGGED_MSG;
    }

    public String getBooksList() {
        return bookService.getList();
    }

    public String printBooksList() {
        return bookService.printList();
    }

    public String operationBook(String bookId, String option, User user) {
        return bookService.elementOperation(bookId, option, user);
    }

    public String getMoviesList() {
        return movieService.getList();
    }

    public String printMoviesList() {
        return movieService.printList();
    }

    public String operationMovie(String movieId, String option, User user) {
        return movieService.elementOperation(movieId, option, user);
    }

    public User userLogIn(String libraryNumber, String password) {
        return userService.userLogIn(libraryNumber, password);
    }

    public String getUserProfile(User user) {
        return userService.getUserProfileInformation(user);
    }

}
