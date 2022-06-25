package com.jpmc.theater.repository;

import com.jpmc.theater.model.Showing;

import java.util.List;

public interface ShowingRepository {

    // method to get all showings from the data source
    List<Showing> getAllShowings();

    void addShowing(Showing showing);

    Showing getShowing(int showingId);

    Showing getShowingBySequence(int sequenceId);
}
