package com.twu.biblioteca.entity;

import com.twu.biblioteca.exception.InvalidRatingException;

public class Movie extends BibliotecaProduct {

    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        if((rating != null) && ((rating < 1) || (rating > 10))){
            throw new InvalidRatingException();
        }
        this.rating = rating;
    }
}
