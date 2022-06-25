package com.jpmc.theater.service;

import com.jpmc.theater.model.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation reserveMovie(int customerId, int sequenceId, int numOfTickets);

    List<Reservation> getAllReservations();
}
