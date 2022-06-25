package com.jpmc.theater.stubs;

import com.jpmc.theater.model.discount.Discount;
import com.jpmc.theater.repository.DiscountRepository;

import java.util.ArrayList;
import java.util.List;

public class DiscountRepositoryStub implements DiscountRepository {
    List<Discount> discountList = new ArrayList<>();

    @Override
    public void addDiscount(Discount discount) {
        discountList.add(discount);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountList;
    }
}
