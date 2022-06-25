package com.jpmc.theater.tests;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.discount.SequenceNumberDiscount;
import com.jpmc.theater.model.discount.ShowTimeRangeDiscount;
import com.jpmc.theater.model.discount.SpecialMovieDiscount;
import com.jpmc.theater.model.enums.DiscountType;
import com.jpmc.theater.repository.DiscountRepository;
import com.jpmc.theater.repository.MovieRepository;
import com.jpmc.theater.repository.ReservationRepository;
import com.jpmc.theater.repository.ShowingRepository;
import com.jpmc.theater.service.DiscountService;
import com.jpmc.theater.service.MovieService;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.ShowingService;
import com.jpmc.theater.service.impl.DiscountServiceImpl;
import com.jpmc.theater.service.impl.MovieServiceImpl;
import com.jpmc.theater.service.impl.ReservationServiceImpl;
import com.jpmc.theater.service.impl.ShowingServiceImpl;
import com.jpmc.theater.stubs.DiscountRepositoryStub;
import com.jpmc.theater.stubs.MovieRepositoryStub;
import com.jpmc.theater.stubs.ReservationRepositoryStub;
import com.jpmc.theater.stubs.ShowingRepositoryStub;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReservationTests {
    private ShowingRepository showingRepository;
    private ShowingService showingService;
    private MovieService movieService;
    private MovieRepository movieRepository;
    private DiscountService discountService;
    private DiscountRepository discountRepository;
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;

    @Before
    public void setup() {
        movieRepository = new MovieRepositoryStub();
        movieService = new MovieServiceImpl(movieRepository);
        movieService.addMovie(3, "The Batman", Duration.ofMinutes(95), 9, 0);

        showingRepository = new ShowingRepositoryStub();
        showingService = new ShowingServiceImpl(showingRepository);

        LocalDate date = LocalDate.of(2022, 5, 5);

        showingService.addShowing(1, 3, LocalDateTime.of(date, LocalTime.of(9, 0)), 1);

        discountRepository = new DiscountRepositoryStub();
        discountService = new DiscountServiceImpl(discountRepository, showingService, movieService);

        SpecialMovieDiscount specialMovieDiscount = new SpecialMovieDiscount(DiscountType.SPECIALMOVIE, 0, 10);
        discountService.addDiscount(specialMovieDiscount);

        ShowTimeRangeDiscount showTimeRangeDiscount = new ShowTimeRangeDiscount(5, 0,
                LocalDateTime.of(date, LocalTime.of(11, 0)),
                LocalDateTime.of(date, LocalTime.of(16, 0)));
        discountService.addDiscount(showTimeRangeDiscount);

        SequenceNumberDiscount sequenceNumberDiscount = new SequenceNumberDiscount(0, 5, 3);
        discountService.addDiscount(sequenceNumberDiscount);

        sequenceNumberDiscount = new SequenceNumberDiscount(6, 0, 4);
        discountService.addDiscount(sequenceNumberDiscount);

        reservationRepository = new ReservationRepositoryStub();
        reservationService = new ReservationServiceImpl(discountService, showingService, reservationRepository);
    }

    @Test
    public void newReservation() {
        Reservation reservation = reservationService.reserveMovie(1, 1, 3);
        assertTrue(reservation.getTotalFee() == 27); //No discounts applied
        assertEquals(1, reservationService.getAllReservations().size());
    }
}
