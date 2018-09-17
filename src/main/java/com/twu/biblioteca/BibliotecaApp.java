package com.twu.biblioteca;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.BibliotecaService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

    private static final String INVALID_BOOK_CHECKOUT_RESPONSE = "That book is not available";
    private static final String NO_BOOKS_TO_CHECKOUT_RESPONSE = "There are no books available to check out";
    private static final String INVALID_MOVIE_CHECKOUT_RESPONSE = "That movie is not available";
    private static final String NO_MOVIES_TO_CHECKOUT_RESPONSE = "There are no movies available to check out";
    private static final String INVALID_USER_RESPONSE = "This is not a valid user";
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BibliotecaService bibliotecaService;

    public static void main(String[] args) throws IOException {
        createBibliotecaService();
        System.out.println(bibliotecaService.getWelcomeMessage());
        String menuOptionResult = "";
        while (!menuOptionResult.equals("Quit")) {
            System.out.println("\nMenu");
            System.out.println(bibliotecaService.getMainMenuMessage());
            System.out.print("Select an option: ");
            String option = bufferedReader.readLine();
            menuOptionResult = selectMenuOption(option);
            if (!menuOptionResult.equals("Quit")) {
                System.out.println(menuOptionResult);
            }
        }
    }

    static String selectMenuOption(String option) throws IOException {
        switch (option) {
            case "1":
                return bibliotecaService.getBooksList();
            case "2":
                User userCheckOut = logInUser();
                if (userCheckOut != null) {
                    return getCheckOutBookOperation(option, userCheckOut);
                }
                return INVALID_USER_RESPONSE;
            case "3":
                User userReturn = logInUser();
                if (userReturn != null) {
                    return getReturnBookOperation(option, userReturn);
                }
                return INVALID_USER_RESPONSE;
            case "4":
                return bibliotecaService.getMoviesList();
            case "5":
                return getMovieOperation();
            case "6":
                return "Quit";
            default:
                return "Select a valid option!\n";
        }
    }

    private static User logInUser() throws IOException {
        System.out.println("Introduce your library number: ");
        String libraryNumber = bufferedReader.readLine();
        System.out.println("Introduce your password: ");
        String password = bufferedReader.readLine();
        return bibliotecaService.userLogIn(libraryNumber, password);
    }

    private static String getCheckOutBookOperation(String option, User user) throws IOException {
        String booksAvailable = bibliotecaService.printBooksList();
        if (booksAvailable.isEmpty()) {
            return BibliotecaApp.NO_BOOKS_TO_CHECKOUT_RESPONSE;
        }
        System.out.println("Books available: ");
        System.out.println(booksAvailable);
        while (true) {
            System.out.print("Select an option: ");
            String bookSelection = bufferedReader.readLine();
            String bookResult = bibliotecaService.operationBook(bookSelection, option, user);
            if (!bookResult.equals(BibliotecaApp.INVALID_BOOK_CHECKOUT_RESPONSE)) {
                return bookResult;
            }
            System.out.println(bookResult);
        }
    }

    private static String getReturnBookOperation(String option, User user) {
        return bibliotecaService.operationBook("", option, user);
    }

    private static String getMovieOperation() throws IOException {
        String moviesAvailable = bibliotecaService.printMoviesList();
        if (moviesAvailable.isEmpty()) {
            return NO_MOVIES_TO_CHECKOUT_RESPONSE;
        }
        System.out.println("Movies available:");
        System.out.println(moviesAvailable);
        while (true) {
            System.out.print("Select an option: ");
            String movieSelection = bufferedReader.readLine();
            String movieResult = bibliotecaService.operationMovie(movieSelection);
            if (!movieResult.equals(INVALID_MOVIE_CHECKOUT_RESPONSE)) {
                return movieResult;
            }
            System.out.println(movieResult);
        }
    }

    private static void createBibliotecaService() {
        BookRepository bookRepository = new BookRepository();
        MovieRepository movieRepository = new MovieRepository();
        UserRepository userRepository = new UserRepository();
        BookService bookService = new BookService(bookRepository);
        MovieService movieService = new MovieService(movieRepository);
        UserService userService = new UserService(userRepository);
        bibliotecaService = new BibliotecaService(bookService, movieService, userService);
    }

}
