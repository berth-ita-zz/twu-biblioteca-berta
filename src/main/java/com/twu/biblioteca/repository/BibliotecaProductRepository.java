package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.BibliotecaProduct;

import java.util.ArrayList;
import java.util.List;

public abstract class BibliotecaProductRepository<T extends BibliotecaProduct> {

    protected List<T> availableList = new ArrayList<>();
    protected List<T> returnList = new ArrayList<>();

    public List<T> getList() {
        return availableList;
    }

    public List<T> getReturnList() {
        return returnList;
    }

    public Boolean deleteFromList(String elementId) {
        return getListOperationResult(elementId, availableList, returnList);
    }

    public Boolean returnFromList(String elementId) {
        return getListOperationResult(elementId, returnList, availableList);
    }

    private Boolean getListOperationResult(String elementId, List<T> listToDelete, List<T> listToAdd) {
        if (!elementIdIsNumeric(elementId)) return false;
        if (checkElementIdIsOnList(elementId, listToDelete)) return false;
        listToAdd.add(listToDelete.get(Integer.parseInt(elementId) - 1));
        listToDelete.remove(Integer.parseInt(elementId) - 1);
        return true;
    }

    private boolean checkElementIdIsOnList(String bookNumber, List<T> bookList) {
        return (Integer.parseInt(bookNumber) <= 0) || (Integer.parseInt(bookNumber) > bookList.size());
    }

    private static boolean elementIdIsNumeric(String elementId) {
        return elementId.matches("-?\\d+?");
    }

}
