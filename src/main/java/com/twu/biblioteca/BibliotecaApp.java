package com.twu.biblioteca;

import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.service.BibliotecaService;
import com.twu.biblioteca.service.BookService;
import com.twu.biblioteca.service.MovieService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

    private static final String INVALID_CHECKOUT_BOOK_RESPONSE = "That book is not available";
    private static final String INVALID_RETURN_BOOK_RESPONSE = "This is not a valid book to return";
    private static final String NO_BOOKS_TO_CHECKOUT_RESPONSE = "There are no books available to check out";
    private static final String NO_BOOKS_TO_RETURN_RESPONSE = "There are no books available to return";
    private static final String INVALID_CHECKOUT_MOVIE_RESPONSE = "That movie is not available";
    private static final String INVALID_RETURN_MOVIE_RESPONSE = "This is not a valid movie to return";
    private static final String NO_MOVIE_TO_CHECKOUT_RESPONSE = "There are no movies available to check out";
    private static final String NO_MOVIE_TO_RETURN_RESPONSE = "There are no movies available to return";
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BibliotecaService bibliotecaService;

    public static void main(String[] args) throws IOException {
        BookRepository bookRepository = new BookRepository();
        MovieRepository movieRepository = new MovieRepository();
        BookService bookService = new BookService(bookRepository);
        MovieService movieService = new MovieService(movieRepository);
        bibliotecaService = new BibliotecaService(bookService, movieService);
        System.out.println(bibliotecaService.getWelcomeMessage());
        String menuOptionResult = "";
        while(!menuOptionResult.equals("Quit")) {
            System.out.println("\nMenu");
            System.out.println(bibliotecaService.getMainMenuMessage());
            System.out.print("Select an option: ");
            String option = bufferedReader.readLine();
            menuOptionResult = selectMenuOption(option);
            if(!menuOptionResult.equals("Quit")) {
                System.out.println(menuOptionResult);
            }
        }
    }

    public static String selectMenuOption(String option) throws IOException {
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
                return "Quit";
            default:
                return "Select a valid option!\n";
        }
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

}
