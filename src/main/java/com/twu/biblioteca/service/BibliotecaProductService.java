package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.BibliotecaProduct;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.BibliotecaProductRepository;

import java.util.List;

public abstract class BibliotecaProductService<T extends BibliotecaProduct> {

    protected BibliotecaProductRepository<T> bibliotecaProductRepository;

    public String getList() {
        List<T> productList = bibliotecaProductRepository.getList();
        String elementListToPrint = "";
        for (T element: productList) {
            elementListToPrint = elementListToPrint.concat(getElementWithFormat(element));
        }
        return elementListToPrint;
    }

    public String printList() {
        List<T> elementList = bibliotecaProductRepository.getList();

        String elementListToPrint = "";
        for (T element : elementList) {
            elementListToPrint = elementListToPrint.concat(getElementWithFormatAndId(element));
        }
        return elementListToPrint;
    }

    public String elementOperation(String elementId, String option, User user) {
        if(option.equals(getCheckOutOption())) {
            if (bibliotecaProductRepository.loggedUserCheckOutElement(elementId, user)) {
                return "Thank you! Enjoy the " + getElementName();
            }
            return "That " + getElementName() + " is not available";
        }
        if(bibliotecaProductRepository.loggedUserReturnElement(user)) {
            return "Thank you for returning the " + getElementName();
        }
        return "This is not a valid " + getElementName() + " to return";
    }

    protected abstract String getElementWithFormat(T element);

    protected abstract String getElementWithFormatAndId(T element);

    protected abstract String getCheckOutOption();

    protected abstract String getElementName();

}
