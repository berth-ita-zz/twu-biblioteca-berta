package com.twu.biblioteca.util;

public class NumericUtils {

    public static boolean checkElementIsOnList(String bookNumber, Integer listSize) {
        return (Integer.parseInt(bookNumber) <= 0) || (Integer.parseInt(bookNumber) > listSize);
    }

    public static boolean numberIsNumeric(String bookNumber) {
        return bookNumber.matches("-?\\d+(\\.\\d+)?");
    }

}
