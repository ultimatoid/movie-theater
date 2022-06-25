package com.jpmc.theater.model;

import java.time.LocalDateTime;

public class ShowingJSON {
    private int showingId;
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public ShowingJSON(int showingId, Movie movie, LocalDateTime showStartTime, int sequenceOfTheDay) {
        this.showingId = showingId;
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    public int getShowingId() {
        return showingId;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

}
