package com.jpmc.theater.service;

import com.jpmc.theater.model.discount.Discount;

import java.util.List;

public interface DiscountService {

    void addDiscount(Discount discount);

    public List<Discount> getAllDiscounts();

    public double calculateTicketPrice(int showingId);

    //this method will go through all applicable discounts and return the max discount available to the user
    public double getMaxDiscount(int showingId);
}
