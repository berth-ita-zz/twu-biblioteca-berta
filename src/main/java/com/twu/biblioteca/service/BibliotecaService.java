package com.twu.biblioteca.service;

public class BibliotecaService {

    private BookService bookService;


    public BibliotecaService(BookService bookService) {
        this.bookService = bookService;
    }

    public String getWelcomeMessage() {
        return "Welcome to La Biblioteca!";
    }

    public String getMainMenuMessage() {
        return "1 - List books\n2 - Check out book\n3 - Return book\n4 - Quit\n";
    }

    public String getBooksList() {
        return bookService.getBooksList();
    }

    public String printBooksList(String option){
        return bookService.printBooksList(option);
    }

    public String operationBook(String bookNumber, String option){
        return bookService.operationBook(bookNumber, option);
    }

}
