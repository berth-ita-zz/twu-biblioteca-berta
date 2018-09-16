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

    private static final String INVALID_CHECKOUT_RESPONSE = "That book is not available";
    private static final String INVALID_RETURN_RESPONSE = "This is not a valid book to return";
    private static final String NO_BOOKS_TO_CHECKOUT_RESPONSE = "There are no books available to check out";
    private static final String NO_BOOKS_TO_RETURN_RESPONSE = "There are no books available to return";
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BibliotecaService bibliotecaService;

    public static void main(String[] args) throws IOException {
        createBibliotecaService();
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

    static String selectMenuOption(String option) throws IOException {
        switch (option) {
            case "1":
                String bookList = bibliotecaService.getBooksList();
                return bookList;
            case "2":
                String booksAvailableToCheckOut = bibliotecaService.printBooksList(option);
                if(booksAvailableToCheckOut.isEmpty()) {
                    return NO_BOOKS_TO_CHECKOUT_RESPONSE;
                }
                System.out.println("Books available:");
                System.out.println(booksAvailableToCheckOut);
                while(true) {
                    System.out.print("Select an option: ");
                    String bookToCheckOut = bufferedReader.readLine();
                    String bookCheckOutResult = bibliotecaService.operationBook(bookToCheckOut, option);
                    if (!bookCheckOutResult.equals(INVALID_CHECKOUT_RESPONSE)) {
                        return bookCheckOutResult;
                    }
                    System.out.println(bookCheckOutResult);
                }
            case "3":
                String booksAvailableToReturn = bibliotecaService.printBooksList(option);
                if(booksAvailableToReturn.isEmpty()) {
                    return NO_BOOKS_TO_RETURN_RESPONSE;
                }
                System.out.println("Which book do you want to return? ");
                System.out.println(booksAvailableToReturn);
                while(true) {
                    System.out.print("Select an option: ");
                    String bookToReturn= bufferedReader.readLine();
                    String bookReturnResult = bibliotecaService.operationBook(bookToReturn, option);
                    if (!bookReturnResult.equals(INVALID_RETURN_RESPONSE)) {
                        return bookReturnResult;
                    }
                    System.out.println(bookReturnResult);
                }
            case "4":
                String movieList = bibliotecaService.getMoviesList();
                return movieList;
            case "5":
                return "Quit";
            default:
                return "Select a valid option!\n";
        }
    }

    private static void createBibliotecaService() {
        BookRepository bookRepository = new BookRepository();
        MovieRepository movieRepository = new MovieRepository();
        BookService bookService = new BookService(bookRepository);
        MovieService movieService = new MovieService(movieRepository);
        bibliotecaService = new BibliotecaService(bookService, movieService);
    }

}
