package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.BibliotecaProduct;
import com.twu.biblioteca.entity.User;

import java.util.ArrayList;
import java.util.List;

public abstract class BibliotecaProductRepository<T extends BibliotecaProduct> {

    protected List<T> availableList = new ArrayList<>();

    public List<T> getList() {
        return availableList;
    }

    public Boolean loggedUserCheckOutElement(String elementId, User user) {
        if(getCheckedOutElement(user) == null) {
            if (!verifyElementNumber(elementId, availableList)) return false;
            setElementCheckedOut(user, availableList.get(Integer.parseInt(elementId) - 1));
            availableList.remove(Integer.parseInt(elementId) - 1);
            return true;
        }
        return false;
    }

    public Boolean loggedUserReturnElement(User user) {
        if(getCheckedOutElement(user) != null) {
            availableList.add(getCheckedOutElement(user));
            setElementCheckedOut(user, null);
            return true;
        }
        return false;
    }

    private boolean checkElementIdIsOnList(String elementId, List<T> elementList) {
        return (Integer.parseInt(elementId) <= 0) || (Integer.parseInt(elementId) > elementList.size());
    }

    private static boolean elementIdIsNumeric(String elementId) {
        return elementId.matches("-?\\d+");
    }

    private boolean verifyElementNumber(String elementId, List<T> listToDelete) {
        if (!elementIdIsNumeric(elementId)) {
            System.out.println("I'm here");
            return false;
        }
        return !checkElementIdIsOnList(elementId, listToDelete);
    }

    protected abstract T getCheckedOutElement(User user);

    protected abstract void setElementCheckedOut(User user, T t);

}
