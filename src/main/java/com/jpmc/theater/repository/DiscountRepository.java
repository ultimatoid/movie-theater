package com.jpmc.theater.repository;

import com.jpmc.theater.model.discount.Discount;

import java.util.List;

public interface DiscountRepository {
    void addDiscount(Discount discount);

    List<Discount> getAllDiscounts();
}
