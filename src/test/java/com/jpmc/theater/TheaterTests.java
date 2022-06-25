package com.jpmc.theater;

import com.jpmc.theater.service.impl.MovieServiceImpl;
import com.jpmc.theater.service.impl.ShowingServiceImpl;
import com.jpmc.theater.service.impl.TheaterServiceImpl;
import com.jpmc.theater.stubs.MovieRepositoryStub;
import com.jpmc.theater.stubs.ShowingRepositoryStub;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TheaterTests {
    private ShowingRepositoryStub showingRepository;
    private ShowingServiceImpl showingService;
    private MovieServiceImpl movieService;
    private MovieRepositoryStub movieRepository;
    private TheaterServiceImpl theaterService;
    @Before
    public void setup() {
        movieRepository = new MovieRepositoryStub();

        movieService = new MovieServiceImpl(movieRepository);
        movieService.addMovie(1, "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        movieService.addMovie(2, "Turning Red", Duration.ofMinutes(85), 11, 0);
        movieService.addMovie(3, "The Batman", Duration.ofMinutes(95), 9, 0);

        showingRepository = new ShowingRepositoryStub();

        showingService = new ShowingServiceImpl(showingRepository);

        showingService.addShowing(1, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)), 1);
        showingService.addShowing(2, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0)),2);
        showingService.addShowing(3, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 50)),3);
        showingService.addShowing(4, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 30)), 4);
        showingService.addShowing(5, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 10)), 5);
        showingService.addShowing(6, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 50)), 6);
        showingService.addShowing(7, 2, LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30)), 7);
        showingService.addShowing(8, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 10)), 8);
        showingService.addShowing(9, 3, LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 0)), 9);
        theaterService = new TheaterServiceImpl(showingService, movieService);
    }

    @Test
    public void printMovieSchedule() {
        theaterService.printSchedule();
    }

    @Test
    public void printMovieScheduleJson() {
        theaterService.printSchedule(true);
    }
}
