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

import static com.twu.biblioteca.util.ConstantUtils.*;

public class BibliotecaApp {

    private static User user;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BibliotecaService bibliotecaService;

    public static void main(String[] args) throws IOException {
        initializeApplication();
        System.out.println(bibliotecaService.getWelcomeMessage());
        String menuOptionResult = "";
        while (true) {
            System.out.println("\nMenu");
            if (user != null) {
                System.out.println(bibliotecaService.getMainMenuMessageUserLogged());
                System.out.print("Select an option: ");
                String option = bufferedReader.readLine();
                menuOptionResult = selectMenuOptionUserLogged(option);
            } else {
                System.out.println(bibliotecaService.getMainMenuMessageUserNotLogged());
                System.out.print("Select an option: ");
                String option = bufferedReader.readLine();
                menuOptionResult = selectMenuOptionUserNotLogged(option);
            }
            if (menuOptionResult.equals("Quit")) {
                break;
            }
            System.out.println(menuOptionResult);
        }
    }

    private static String selectMenuOptionUserNotLogged(String option) throws IOException {
        switch (option) {
            case "1":
                return bibliotecaService.getBooksList();
            case "2":
                return bibliotecaService.getMoviesList();
            case "3":
                user = logInUser();
                if (user != null) {
                    return VALID_USER_RESPONSE;
                }
                return INVALID_USER_RESPONSE;
            case "4":
                return "Quit";
            default:
                return "Select a valid option!\n";
        }
    }

    public static String selectMenuOptionUserLogged(String option) throws IOException {
        switch (option) {
            case "1":
                return bibliotecaService.getBooksList();
            case "2":
                return getBookOperation(option, NO_BOOKS_TO_CHECKOUT_RESPONSE, "Books available:",
                        INVALID_CHECKOUT_BOOK_RESPONSE);
            case "3":
                return getBookOperation(option, NO_BOOKS_TO_RETURN_RESPONSE, "Which book do you want to return? ",
                        INVALID_RETURN_BOOK_RESPONSE);
            case "4":
                return bibliotecaService.getMoviesList();
            case "5":
                return getMovieOperation(option, NO_MOVIE_TO_CHECKOUT_RESPONSE, "Movies available:",
                        INVALID_CHECKOUT_MOVIE_RESPONSE);
            case "6":
                return getMovieOperation(option, NO_MOVIE_TO_RETURN_RESPONSE, "Which movie do you want to return? ",
                        INVALID_RETURN_MOVIE_RESPONSE);
            case "7":
                user = null;
                return LOG_OUT_RESPONSE;
            case "8":
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

    private static String getBookOperation(String option, String noBooksToCheckoutResponse, String userMessage,
                                           String invalidCheckoutResponse) throws IOException {
        String booksAvailableToCheckOut = bibliotecaService.printBooksList(option);
        if (booksAvailableToCheckOut.isEmpty()) {
            return noBooksToCheckoutResponse;
        }
        System.out.println(userMessage);
        System.out.println(booksAvailableToCheckOut);
        while (true) {
            System.out.print("Select an option introducing book number: ");
            String bookToCheckOut = bufferedReader.readLine();
            String bookCheckOutResult = bibliotecaService.operationBook(bookToCheckOut, option);
            if (!bookCheckOutResult.equals(invalidCheckoutResponse)) {
                return bookCheckOutResult;
            }
            System.out.println(bookCheckOutResult);
        }
    }

    private static String getMovieOperation(String option, String noMoviesToCheckoutResponse, String userMessage,
                                            String invalidCheckoutResponse) throws IOException {
        String moviesAvailableToCheckOut = bibliotecaService.printMoviesList(option);
        if (moviesAvailableToCheckOut.isEmpty()) {
            return noMoviesToCheckoutResponse;
        }
        System.out.println(userMessage);
        System.out.println(moviesAvailableToCheckOut);
        while (true) {
            System.out.print("Select an option introducing movie number: ");
            String movieToCheckOut = bufferedReader.readLine();
            String movieCheckOutResult = bibliotecaService.operationMovie(movieToCheckOut, option);
            if (!movieCheckOutResult.equals(invalidCheckoutResponse)) {
                return movieCheckOutResult;
            }
            System.out.println(movieCheckOutResult);
        }
    }

    private static void initializeApplication() {
        BookRepository bookRepository = new BookRepository();
        MovieRepository movieRepository = new MovieRepository();
        UserRepository userRepository = new UserRepository();
        BookService bookService = new BookService(bookRepository);
        MovieService movieService = new MovieService(movieRepository);
        UserService userService = new UserService(userRepository);
        bibliotecaService = new BibliotecaService(bookService, movieService, userService);
    }

}
