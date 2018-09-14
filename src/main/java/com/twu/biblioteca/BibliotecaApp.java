package com.twu.biblioteca;

import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.service.BibliotecaService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

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
                System.out.println(bibliotecaService.printBooksList());
                Boolean validBook = false;
                while(!validBook) {
                    System.out.print("Select an option: ");
                    String bookToCheckOut = bufferedReader.readLine();
                    String bookCheckOutResult = bibliotecaService.checkOutBook(bookToCheckOut);
                    if (!bookCheckOutResult.equals("That book is not available")) {
                        return bookCheckOutResult;
                    }
                    System.out.println(bookCheckOutResult);
                    validBook = false;
                }
            case "3":
                return "Quit";
            default:
                return "Select a valid option!\n";
        }
    }

}
