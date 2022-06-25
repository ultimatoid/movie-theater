package com.jpmc.theater.stubs;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepositoryStub implements MovieRepository {
    List<Movie> movieList = new ArrayList<>();
    @Override
    public List<Movie> getAllMovies() {
        return movieList;
    }

    @Override
    public void addMovie(Movie movie) {
        this.movieList.add(movie);
    }

    @Override
    public Movie getMovie(int movieId) {
        Optional<Movie> movie = movieList.stream().filter(mov -> mov.getMovieId() == movieId).findFirst();

        if (movie.isPresent())
            return movie.get();

        // handle error that movie with Id is not found
        return null;
    }
}
