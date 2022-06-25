package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.repository.ShowingRepository;
import com.jpmc.theater.service.ShowingService;

import java.time.LocalDateTime;
import java.util.List;

public class ShowingServiceImpl implements ShowingService {

    ShowingRepository showingRepository;

    public ShowingServiceImpl(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    @Override
    public void addShowing(int showingId, int movieId, LocalDateTime showStartTime, int sequenceOfTheDay) {
        Showing showing = new Showing(showingId, movieId, showStartTime, sequenceOfTheDay);
        showingRepository.addShowing(showing);
    }

    @Override
    public List<Showing> getAllShowings() {
        return showingRepository.getAllShowings();
    }

    @Override
    public Showing getShowing(int showingId) {
        return showingRepository.getShowing(showingId);
    }

    @Override
    public Showing getShowingBySequence(int sequenceId) {
        return showingRepository.getShowingBySequence(sequenceId);
    }
}
