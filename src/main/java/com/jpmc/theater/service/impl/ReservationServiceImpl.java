package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.repository.ReservationRepository;
import com.jpmc.theater.service.DiscountService;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.ShowingService;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    DiscountService discountService;
    ShowingService showingService;
    ReservationRepository reservationRepository;

    public ReservationServiceImpl(DiscountService discountService, ShowingService showingService, ReservationRepository reservationRepository) {
        this.discountService = discountService;
        this.showingService = showingService;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation reserveMovie(int customerId, int sequenceId, int numOfTickets) {
        Showing showing = showingService.getShowingBySequence(sequenceId);
        double ticketPrice = discountService.calculateTicketPrice(showing.getShowingId());
        Reservation reservation = new Reservation(customerId, sequenceId, numOfTickets, ticketPrice * numOfTickets);
        boolean status = addReservation(reservation);
        if (status)
            return reservation;

        //reservation failed, handle error here
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

    private boolean addReservation(Reservation reservation) {
        return reservationRepository.addReservation(reservation);
    }
}
