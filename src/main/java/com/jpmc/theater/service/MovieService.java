package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;

import java.time.Duration;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    void addMovie(int movieId, String title, Duration runningTime, double ticketPrice, int specialCode);

    Movie getMovie(int movieId);
}
