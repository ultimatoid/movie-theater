package com.jpmc.theater.model.discount;

import com.jpmc.theater.model.enums.DiscountType;

public class DateDiscount extends Discount {
    private int date;
    private int month;
    private int year;

    public DateDiscount(double amount, double percent, int date) {
        this(amount, percent, date, 0, 0);
    }

    public DateDiscount(double amount, double percent, int date, int month, int year) {
        super(DiscountType.DATE, amount, percent);
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public int getDate() {
        return date;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
