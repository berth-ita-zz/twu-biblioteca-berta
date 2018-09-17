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
            for (int i = 0; i < availableList.size(); i++) {
                if(availableList.get(i).getId().equals(elementId)){
                    setElementCheckedOut(user, availableList.get(i));
                    availableList.remove(i);
                    return true;
                }
            }
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

    protected abstract T getCheckedOutElement(User user);

    protected abstract void setElementCheckedOut(User user, T t);

}
