package com.twu.biblioteca;

import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.BibliotecaService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;
import com.twu.biblioteca.util.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import static com.twu.biblioteca.util.ConstantUtils.*;
import static com.twu.biblioteca.util.Output.*;

public class BibliotecaApp {

    static User user;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BibliotecaService bibliotecaService;

    public static void main(String[] args) throws Exception {
        initializeApplication();
        printMessageToUser(bibliotecaService.getWelcomeMessage());
        String menuOptionResult;
        while (true) {
            printMessageToUser(MENU_MSG);
            if (user != null) {
                printMessageToUser(bibliotecaService.getMainMenuMessageUserLogged());
                printMessageToUser(SELECT_OPTION_MSG);
                String option = bufferedReader.readLine();
                menuOptionResult = selectMenuOptionUserLogged(option);
            } else {
                printMessageToUser(bibliotecaService.getMainMenuMessageUserNotLogged());
                printMessageToUser(SELECT_OPTION_MSG);
                String option = bufferedReader.readLine();
                menuOptionResult = selectMenuOptionUserNotLogged(option);
            }
            if (menuOptionResult.equals(QUIT_MSG)) {
                break;
            }
            printMessageToUser(menuOptionResult);
        }
    }

    public static String selectMenuOptionUserNotLogged(String option) throws IOException {
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
                return QUIT_MSG;
            default:
                return SELECT_A_VALID_OPTION_MSG;
        }
    }

    public static String selectMenuOptionUserLogged(String option) throws IOException {
        switch (option) {
            case "1":
                return bibliotecaService.getBooksList();
            case "2":
                return getCheckOutBookOperation(option, user);
            case "3":
                return getReturnBookOperation(option, user);
            case "4":
                return bibliotecaService.getMoviesList();
            case "5":
                return getCheckOutMovieOperation(option, user);
            case "6":
                return getReturnMovieOperation(option, user);
            case "7":
                return bibliotecaService.getUserProfile(user);
            case "8":
                user = null;
                return LOG_OUT_RESPONSE;
            case "9":
                return QUIT_MSG;
            default:
                return SELECT_A_VALID_OPTION_MSG;
        }
    }

    private static User logInUser() throws IOException {
        printMessageToUser(INTRODUCE_YOUR_LIBRARY_NUMBER_MSG);
        String libraryNumber = bufferedReader.readLine();
        printMessageToUser(INTRODUCE_YOUR_PASSWORD_MSG);
        String password = bufferedReader.readLine();
        return bibliotecaService.userLogIn(libraryNumber, password);
    }

    private static String getCheckOutBookOperation(String option, User user) throws IOException {
        String booksAvailable = bibliotecaService.printBooksList();
        if(user.getBookCheckedOut() != null) {
            return ALREADY_CHECKOUT_ELEMENT_RESPONSE;
        }
        if (booksAvailable.isEmpty()) {
            return NO_BOOKS_TO_CHECKOUT_RESPONSE;
        }
        printMessageToUser(BOOKS_AVAILABLE_MSG);
        printMessageToUser(booksAvailable);
        while (true) {
            printMessageToUser(SELECT_AN_OPTION_INTRODUCING_BOOK_NUMBER_MSG);
            String bookSelection = bufferedReader.readLine();
            String bookResult = bibliotecaService.operationBook(bookSelection, option, user);
            if (!bookResult.equals(INVALID_CHECKOUT_BOOK_RESPONSE)) {
                return bookResult;
            }
            printMessageToUser(bookResult);
        }
    }

    private static String getReturnBookOperation(String option, User user) {
        return bibliotecaService.operationBook("", option, user);
    }

    private static String getCheckOutMovieOperation(String option, User user) throws IOException {
        String moviesAvailable = bibliotecaService.printMoviesList();
        if(user.getMovieCheckedOut() != null) {
            return ALREADY_CHECKOUT_ELEMENT_RESPONSE;
        }
        if (moviesAvailable.isEmpty()) {
            return NO_MOVIE_TO_CHECKOUT_RESPONSE;
        }
        printMessageToUser(MOVIES_AVAILABLE_MSG);
        printMessageToUser(moviesAvailable);
        while (true) {
            printMessageToUser(SELECT_AN_OPTION_INTRODUCING_MOVIE_NUMBER_MSG);
            String movieSelection = bufferedReader.readLine();
            String movieResult = bibliotecaService.operationMovie(movieSelection, option, user);
            if (!movieResult.equals(INVALID_CHECKOUT_MOVIE_RESPONSE)) {
                return movieResult;
            }
            printMessageToUser(movieResult);
        }
    }

    private static String getReturnMovieOperation(String option, User user) {
        return bibliotecaService.operationMovie("", option, user);
    }

    private static void initializeApplication() throws Exception {
        BookRepository bookRepository = getBookRepository();
        MovieRepository movieRepository = getMovieRepository();
        UserRepository userRepository = new UserRepository();
        BookService bookService = new BookService(bookRepository);
        MovieService movieService = new MovieService(movieRepository);
        UserService userService = new UserService(userRepository);
        bibliotecaService = new BibliotecaService(bookService, movieService, userService);
    }

    private static BookRepository getBookRepository() throws Exception {
        FileReader fileReaderBook = getFileReader(BOOK_FILE_NAME);
        BookRepository bookRepository = new BookRepository(fileReaderBook);
        closeFileReader(fileReaderBook);
        return bookRepository;
    }

    private static MovieRepository getMovieRepository() throws Exception {
        FileReader fileReaderMovie = getFileReader(MOVIE_FILE_NAME);
        MovieRepository movieRepository = new MovieRepository(fileReaderMovie);
        closeFileReader(fileReaderMovie);
        return movieRepository;
    }

    private static void closeFileReader(FileReader fileReader) throws Exception {
        fileReader.close();
    }

    private static FileReader getFileReader(String fileName) throws Exception {
        return new FileReader(Objects.requireNonNull(BibliotecaApp.class.getClassLoader().getResource(fileName)).getFile());
    }

}
