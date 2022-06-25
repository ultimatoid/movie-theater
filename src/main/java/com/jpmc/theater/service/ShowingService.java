package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowingService {

    void addShowing(int showingId, int movieId, LocalDateTime showStartTime, int sequenceOfTheDay);

    List<Showing> getAllShowings();

    Showing getShowing(int showingId);

    Showing getShowingBySequence(int sequenceId);
}
