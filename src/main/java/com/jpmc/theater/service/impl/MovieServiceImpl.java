package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.repository.MovieRepository;
import com.jpmc.theater.service.MovieService;

import java.time.Duration;
import java.util.List;

public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    public MovieServiceImpl (MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public void addMovie(int movieId, String title, Duration runningTime, double ticketPrice, int specialCode) {

        // all kinds of validations should be called here before adding movie
        Movie movie = new Movie(movieId, title, runningTime, ticketPrice, specialCode);
        movieRepository.addMovie(movie);
    }

    @Override
    public Movie getMovie(int movieId) {
        return movieRepository.getMovie(movieId);
    }
}
