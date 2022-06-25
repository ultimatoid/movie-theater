package com.jpmc.theater.model;

public class Reservation {
    private int customerId;
    private int showingId;
    private int audienceCount;
    private double totalFee;

    public Reservation(int customerId, int showingId, int audienceCount, double totalFee) {
        this.customerId = customerId;
        this.showingId = showingId;
        this.audienceCount = audienceCount;
        this.totalFee = totalFee;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getShowingId() {
        return showingId;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public double getTotalFee() {
        return totalFee;
    }
}