package com.twu.biblioteca.service;

import org.junit.Test;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class BibliotecaServiceTest {

    @Test
    public void printWelcomeMessageOkTest() {
        BibliotecaService bibliotecaService = new BibliotecaService();
        String welcomeMessage = bibliotecaService.getWelcomeMessage();
        assertThat(welcomeMessage).isEqualTo("Welcome to La Biblioteca!");
    }
}