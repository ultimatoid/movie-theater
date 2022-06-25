package com.jpmc.theater.repository;

import com.jpmc.theater.model.Reservation;

import java.util.List;

public interface ReservationRepository {
    boolean addReservation(Reservation reservation);

    List<Reservation> getAllReservations();
}
