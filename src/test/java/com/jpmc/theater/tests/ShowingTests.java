package com.jpmc.theater.tests;

import com.jpmc.theater.repository.MovieRepository;
import com.jpmc.theater.repository.ShowingRepository;
import com.jpmc.theater.service.MovieService;
import com.jpmc.theater.service.ShowingService;
import com.jpmc.theater.service.impl.MovieServiceImpl;
import com.jpmc.theater.service.impl.ShowingServiceImpl;
import com.jpmc.theater.stubs.MovieRepositoryStub;
import com.jpmc.theater.stubs.ShowingRepositoryStub;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class ShowingTests {
    private ShowingRepository showingRepository;
    private ShowingService showingService;
    private MovieService movieService;
    private MovieRepository movieRepository;

    @Before
    public void setup() {
        movieRepository = new MovieRepositoryStub();

        movieService = new MovieServiceImpl(movieRepository);
        movieService.addMovie(1, "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        movieService.addMovie(2, "Turning Red", Duration.ofMinutes(85), 11, 0);
        movieService.addMovie(3, "The Batman", Duration.ofMinutes(95), 9, 0);

        showingRepository = new ShowingRepositoryStub();
        showingService = new ShowingServiceImpl(showingRepository);

        LocalDate date = LocalDate.of(2022, 5, 5);

        showingService.addShowing(1, 1, LocalDateTime.of(date, LocalTime.of(9, 0)), 1);
        showingService.addShowing(2, 1, LocalDateTime.of(date, LocalTime.of(11, 0)),2);
        showingService.addShowing(3, 3, LocalDateTime.of(date, LocalTime.of(12, 50)),3);
        showingService.addShowing(4, 2, LocalDateTime.of(date, LocalTime.of(14, 30)), 4);
        showingService.addShowing(5, 1, LocalDateTime.of(date, LocalTime.of(16, 10)), 5);
        showingService.addShowing(6, 3, LocalDateTime.of(date, LocalTime.of(17, 50)), 6);
        showingService.addShowing(7, 2, LocalDateTime.of(date, LocalTime.of(19, 30)), 7);
        showingService.addShowing(8, 1, LocalDateTime.of(date, LocalTime.of(21, 10)), 8);
        showingService.addShowing(9, 3, LocalDateTime.of(date, LocalTime.of(23, 0)), 9);
    }

    @Test
    public void showingCount() {
        assertEquals(9, showingService.getAllShowings().size());
    }
}
