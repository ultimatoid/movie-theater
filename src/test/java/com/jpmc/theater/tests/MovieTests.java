package com.jpmc.theater.tests;

import com.jpmc.theater.service.impl.MovieServiceImpl;
import com.jpmc.theater.stubs.MovieRepositoryStub;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MovieTests {
    private MovieServiceImpl movieService;
    private MovieRepositoryStub movieRepository;

    @Before
    public void setup() {
        movieRepository = new MovieRepositoryStub();

        movieService = new MovieServiceImpl(movieRepository);
        movieService.addMovie(1, "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        movieService.addMovie(2, "Turning Red", Duration.ofMinutes(85), 11, 0);
        movieService.addMovie(3, "The Batman", Duration.ofMinutes(95), 9, 0);

    }

    @Test
    public void getAllMovies() {
        assertEquals(3, movieService.getAllMovies().size());
    }
}
