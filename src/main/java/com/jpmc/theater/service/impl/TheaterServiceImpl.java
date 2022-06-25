package com.jpmc.theater.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.ShowingJSON;
import com.jpmc.theater.model.TheatreScheduleJSON;
import com.jpmc.theater.service.TheaterService;
import com.jpmc.theater.util.Utils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public void printSchedule(boolean json) {
        if (json) {
            List<Showing> schedule = showingService.getAllShowings();
            TheatreScheduleJSON jsonSchedule = new TheatreScheduleJSON();
            jsonSchedule.setDate(LocalDate.now());

            jsonSchedule.setShowing(schedule.stream().map(s -> {
                Movie movie = movieService.getMovie(s.getMovieId());
                return new ShowingJSON(s.getShowingId(), movie, s.getShowStartTime(), s.getSequenceOfTheDay());
            }).collect(Collectors.toList()));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(jsonSchedule));
        } else {
            printSchedule();
        }
    }
}
