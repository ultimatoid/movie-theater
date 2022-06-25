package com.jpmc.theater.model.discount;

import com.jpmc.theater.model.enums.DiscountType;

public class SpecialMovieDiscount extends Discount{
    public static int MOVIE_CODE_SPECIAL = 1;

    public SpecialMovieDiscount(DiscountType type, double amount, double percent) {
        super(DiscountType.SPECIALMOVIE, amount, percent);
    }
}
