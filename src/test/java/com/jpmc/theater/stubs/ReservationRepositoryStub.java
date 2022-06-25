package com.jpmc.theater.stubs;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryStub implements ReservationRepository {
    List<Reservation> reservations = new ArrayList<>();

    @Override
    public boolean addReservation(Reservation reservation) {
        reservations.add(reservation);
        return true;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservations;
    }
}