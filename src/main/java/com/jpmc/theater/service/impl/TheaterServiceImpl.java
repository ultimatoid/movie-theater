package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.TheaterService;
import com.jpmc.theater.util.Utils;

import java.time.LocalDate;
import java.util.List;

public class TheaterServiceImpl implements TheaterService {
    ShowingServiceImpl showingService;
    MovieServiceImpl movieService;

    public TheaterServiceImpl(ShowingServiceImpl showingService, MovieServiceImpl movieService) {
        this.showingService = showingService;
        this.movieService = movieService;
    }
    @Override
    public void printSchedule() {
        List<Showing> schedule = showingService.getAllShowings();
        System.out.println(LocalDate.now());
        System.out.println("===================================================");
        schedule.forEach(s -> System.out.println(printShowing(s)));
        System.out.println("===================================================");
    }

    private String printShowing(Showing s) {
        Movie movie = movieService.getMovie(s.getMovieId());
        return s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + movie.getTitle()
                + " " + Utils.humanReadableFormat(movie.getRunningTime()) + " $" + movie.getTicketPrice();
    }

    @Override
    public void printSchedule(String json) {

    }
}
