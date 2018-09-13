package com.twu.biblioteca;

import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.service.BibliotecaService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BookRepository bookRepository = new BookRepository();
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        System.out.println(bibliotecaService.getWelcomeMessage());
        System.out.println("Menu");
        System.out.println(bibliotecaService.getMainMenuMessage());
        System.out.print("Select an option: ");
        String option = bufferedReader.readLine();
        System.out.println("\n" + bibliotecaService.selectMenuOption(option));
    }

}
