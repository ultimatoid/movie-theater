package com.jpmc.theater.model.discount;

import com.jpmc.theater.model.enums.DiscountType;

public class Discount {
    private DiscountType type;
    private double amount;
    private double percent;

    public Discount(DiscountType type, double amount, double percent) {
        this.type = type;
        this.amount = amount;
        this.percent = percent;
    }

    public DiscountType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getPercent() {
        return percent;
    }
}
