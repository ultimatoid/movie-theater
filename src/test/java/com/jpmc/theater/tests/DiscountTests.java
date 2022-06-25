package com.jpmc.theater.tests;

import com.jpmc.theater.model.discount.DateDiscount;
import com.jpmc.theater.model.discount.SequenceNumberDiscount;
import com.jpmc.theater.model.discount.ShowTimeRangeDiscount;
import com.jpmc.theater.model.discount.SpecialMovieDiscount;
import com.jpmc.theater.model.enums.DiscountType;
import com.jpmc.theater.repository.DiscountRepository;
import com.jpmc.theater.repository.MovieRepository;
import com.jpmc.theater.repository.ShowingRepository;
import com.jpmc.theater.service.DiscountService;
import com.jpmc.theater.service.MovieService;
import com.jpmc.theater.service.ShowingService;
import com.jpmc.theater.service.impl.DiscountServiceImpl;
import com.jpmc.theater.service.impl.MovieServiceImpl;
import com.jpmc.theater.service.impl.ShowingServiceImpl;
import com.jpmc.theater.stubs.DiscountRepositoryStub;
import com.jpmc.theater.stubs.MovieRepositoryStub;
import com.jpmc.theater.stubs.ShowingRepositoryStub;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class DiscountTests {
    private ShowingRepository showingRepository;
    private ShowingService showingService;
    private MovieService movieService;
    private MovieRepository movieRepository;
    private DiscountService discountService;
    private DiscountRepository discountRepository;

    @Before
    public void setup() {
        movieRepository = new MovieRepositoryStub();
        movieService = new MovieServiceImpl(movieRepository);
        movieService.addMovie(5, "Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        movieService.addMovie(2, "Turning Red", Duration.ofMinutes(85), 11, 0);

        showingRepository = new ShowingRepositoryStub();
        showingService = new ShowingServiceImpl(showingRepository);
        LocalDate date = LocalDate.of(2022, 5, 5);

        showingService.addShowing(1, 5, LocalDateTime.of(date, LocalTime.of(9, 0)), 1);
        showingService.addShowing(2, 2, LocalDateTime.of(date, LocalTime.of(9, 30)), 2);
        showingService.addShowing(3, 5, LocalDateTime.of(date, LocalTime.of(15, 30)), 3);
        showingService.addShowing(4, 2, LocalDateTime.of(date, LocalTime.of(9, 0)), 4);

        discountRepository = new DiscountRepositoryStub();
        discountService = new DiscountServiceImpl(discountRepository, showingService, movieService);

        SpecialMovieDiscount specialMovieDiscount = new SpecialMovieDiscount(DiscountType.SPECIALMOVIE, 0, 10);
        discountService.addDiscount(specialMovieDiscount);

        ShowTimeRangeDiscount showTimeRangeDiscount = new ShowTimeRangeDiscount(5, 0,
                LocalDateTime.of(date, LocalTime.of(11, 0)),
                LocalDateTime.of(date, LocalTime.of(16, 0)));
        discountService.addDiscount(showTimeRangeDiscount);

        SequenceNumberDiscount sequenceNumberDiscount = new SequenceNumberDiscount(6, 0, 4);
        discountService.addDiscount(sequenceNumberDiscount);

        DateDiscount dateDiscount = new DateDiscount(0, 7,5);
        discountService.addDiscount(dateDiscount);
    }
    @Test
    public void getAllDiscounts() {
        assertEquals(4, discountService.getAllDiscounts().size());
    }

    @Test
    public void getSpecialMovieDiscount10Percent() {
        assertEquals(12.5 * (100-10) / 100, discountService.calculateTicketPrice(1), 0);
    }

    @Test
    public void getTimeRangeDiscount5Dollars() {
        assertEquals(12.5 - 5, discountService.calculateTicketPrice(3), 0);
    }

    @Test
    public void getDiscount_outsideTimeRange() {
        assertEquals(12.5 * (100-10) / 100, discountService.calculateTicketPrice(1), 0);
    }

    @Test
    public void getDiscount_higherSpecialMovieDiscount() {
        assertEquals(12.5 * (100-10) / 100, discountService.calculateTicketPrice(1), 0);
    }

    @Test
    public void getDiscount_NotSpecialMovie() {
        assertEquals(11 - 6, discountService.calculateTicketPrice(4), 0);
    }

    @Test
    public void getDiscount_2ndShow() {
        assertEquals(5, discountService.calculateTicketPrice(4), 0);
    }
}
