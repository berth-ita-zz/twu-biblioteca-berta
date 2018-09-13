package com.twu.biblioteca;

import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.service.BibliotecaService;

public class BibliotecaApp {

    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepository();
        BibliotecaService bibliotecaService = new BibliotecaService(bookRepository);
        System.out.println(bibliotecaService.getWelcomeMessage());
        System.out.println("The books available are: ");
        System.out.print(bibliotecaService.getBooksList());
    }

}
