package com.twu.biblioteca;

import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.service.BibliotecaService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

    public static final String INVALID_CHECKOUT_RESPONSE = "That book is not available";
    public static final String INVALID_RETURN_RESPONSE = "This is not a valid book to return";
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BibliotecaService bibliotecaService;

    public static void main(String[] args) throws IOException {
        BookRepository bookRepository = new BookRepository();
        bibliotecaService = new BibliotecaService(bookRepository);
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
                String bookList = bibliotecaService.getBooksList();
                return bookList;
            case "2":
                System.out.println("Books available:");
                System.out.println(bibliotecaService.printBooksList(option));
                Boolean validCheckOutBook = false;
                while(!validCheckOutBook) {
                    System.out.print("Select an option: ");
                    String bookToCheckOut = bufferedReader.readLine();
                    String bookCheckOutResult = bibliotecaService.operationBook(bookToCheckOut, option);
                    if (!bookCheckOutResult.equals(INVALID_CHECKOUT_RESPONSE)) {
                        return bookCheckOutResult;
                    }
                    System.out.println(bookCheckOutResult);
                    validCheckOutBook = false;
                }
            case "3":
                System.out.println("Which book do you want to return? ");
                System.out.println(bibliotecaService.printBooksList(option));
                Boolean validReturnBook = false;
                while(!validReturnBook) {
                    System.out.print("Select an option: ");
                    String bookToReturn= bufferedReader.readLine();
                    String bookReturnResult = bibliotecaService.operationBook(bookToReturn, option);
                    if (!bookReturnResult.equals(INVALID_RETURN_RESPONSE)) {
                        return bookReturnResult;
                    }
                    System.out.println(bookReturnResult);
                    validReturnBook = false;
                }
            case "4":
                return "Quit";
            default:
                return "Select a valid option!\n";
        }
    }

}
