package com.jpmc.theater.repository;

import com.jpmc.theater.model.Movie;

import java.util.List;

public interface MovieRepository {

    // method to get all movies from the DB
    List<Movie> getAllMovies();

    // add a new movie
    void addMovie(Movie movie);

    Movie getMovie(int movieId);
}
