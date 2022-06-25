package com.jpmc.theater.model;

import com.jpmc.theater.model.Movie;

import java.time.LocalDateTime;

public class Showing {
    private int showingId;
    private int movieId;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(int showingId, int movieId, LocalDateTime showStartTime, int sequenceOfTheDay) {
        this.showingId = showingId;
        this.movieId = movieId;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public int getMovieId() {
        return movieId;
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
