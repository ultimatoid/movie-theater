package com.jpmc.theater.model;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private int movieId;
    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;
    public static double DEFAULT_FULL_PRICE = 25;

    public Movie(int movieId, String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.movieId = movieId;
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getDescription() {
        return description;
    }

    public int getSpecialCode() {
        return specialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return  movie.movieId == movieId
                && movie.ticketPrice ==  ticketPrice
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, title, description, runningTime, ticketPrice, specialCode);
    }
}