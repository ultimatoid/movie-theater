package com.jpmc.theater.model.discount;

import com.jpmc.theater.model.enums.DiscountType;

public class SequenceNumberDiscount extends Discount{
    int sequenceId;

    public SequenceNumberDiscount(double amount, double percent, int sequenceId) {
        super(DiscountType.SEQUENCENUMBER, amount, percent);
        this.sequenceId = sequenceId;
    }

    public int getSequenceId() {
        return sequenceId;
    }
}
