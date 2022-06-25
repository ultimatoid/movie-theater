package com.jpmc.theater.model.discount;

import com.jpmc.theater.model.enums.DiscountType;

import java.time.LocalDateTime;

public class ShowTimeRangeDiscount extends Discount {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ShowTimeRangeDiscount(double amount, double percent, LocalDateTime startTime, LocalDateTime endTime) {
        super(DiscountType.SHOWTIMERANGE, amount, percent);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
