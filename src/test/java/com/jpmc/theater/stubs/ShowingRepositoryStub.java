package com.jpmc.theater.stubs;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.repository.ShowingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowingRepositoryStub implements ShowingRepository {
    List<Showing> showingList = new ArrayList<>();

    @Override
    public List<Showing> getAllShowings() {
        return showingList;
    }

    @Override
    public void addShowing(Showing showing) {
        this.showingList.add(showing);
    }

    @Override
    public Showing getShowing(int showingId) {
        Optional<Showing> showing = showingList.stream().filter(show -> show.getShowingId() == showingId).findFirst();

        if (showing.isPresent())
            return showing.get();
        //throw an error that this showing Id is not found
        return null;
    }

    @Override
    public Showing getShowingBySequence(int sequenceId) {
        Optional<Showing> showing = showingList.stream().filter(show -> show.getSequenceOfTheDay() == sequenceId).findFirst();

        if (showing.isPresent())
            return showing.get();
        //throw an error that this showing Id is not found
        return null;
    }
}
