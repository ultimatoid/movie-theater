package com.jpmc.theater.repository.impl;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.repository.MovieRepository;

import java.util.List;

//to be implemented with real connection to DB
public class MovieRepositoryImpl implements MovieRepository {
    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public Movie getMovie(int movieId) {
        return null;
    }
}
