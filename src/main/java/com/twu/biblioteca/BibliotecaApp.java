package com.twu.biblioteca;

import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.service.BibliotecaService;
import com.twu.biblioteca.service.BookService;

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
        BookRepository bookRepository = new BookRepository();
        BookService bookService = new BookService(bookRepository);
        bibliotecaService = new BibliotecaService(bookService);
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
                        INVALID_CHECKOUT_RESPONSE);
            case "3":
                return getBookOperation(option, NO_BOOKS_TO_RETURN_RESPONSE, "Which book do you want to return? ",
                        INVALID_RETURN_RESPONSE);
            case "4":
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

}
