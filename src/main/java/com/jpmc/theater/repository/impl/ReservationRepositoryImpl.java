package com.jpmc.theater.repository.impl;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.repository.ReservationRepository;

import java.util.List;

//to be implemented with real connection to DB
public class ReservationRepositoryImpl implements ReservationRepository {
    @Override
    public boolean addReservation(Reservation reservation) {
        return true;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return null;
    }
}
