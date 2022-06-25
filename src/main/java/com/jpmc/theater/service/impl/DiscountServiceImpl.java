package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.discount.*;
import com.jpmc.theater.model.enums.DiscountType;
import com.jpmc.theater.repository.DiscountRepository;
import com.jpmc.theater.service.DiscountService;
import com.jpmc.theater.service.MovieService;
import com.jpmc.theater.service.ShowingService;

import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    DiscountRepository discountRepository;
    ShowingService showingService;
    MovieService movieService;

    public DiscountServiceImpl(DiscountRepository discountRepository, ShowingService showingService, MovieService movieService) {
        this.discountRepository = discountRepository;
        this.showingService = showingService;
        this.movieService = movieService;
    }

    @Override
    public void addDiscount(Discount discount) {
        discountRepository.addDiscount(discount);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.getAllDiscounts();
    }

    @Override
    public double calculateTicketPrice(int showingId) {
        Showing showing = showingService.getShowing(showingId);
        if (showing == null) {
            // handle error that showing with showing Id not found
            return Movie.DEFAULT_FULL_PRICE;
        }
        Movie movie = movieService.getMovie(showing.getMovieId());
        if (movie == null) {
            // handle error that movie with movie Id not found
            return Movie.DEFAULT_FULL_PRICE;
        }
        double maxDiscount = calculateDiscountForShowing(showing, movie);
        return movie.getTicketPrice() - maxDiscount;
    }

    @Override
    public double getMaxDiscount(int showingId) {
        double maxDiscount = 0;
        Showing showing = showingService.getShowing(showingId);
        if (showing == null) {
            // handle error that showing with showing Id not found
            return 0;
        }
        Movie movie = movieService.getMovie(showing.getMovieId());
        if (movie == null) {
            // handle error that movie with movie Id not found
            return 0;
        }
        maxDiscount = calculateDiscountForShowing(showing, movie);
        return maxDiscount;
    }

    private double calculateDiscountForShowing(Showing showing, Movie movie) {
        double maxDiscount = 0;
        double currDiscount = 0;
        for (int idx = 0; idx < getAllDiscounts().size(); idx++) {
            Discount discount = getAllDiscounts().get(idx);
            if (discount.getType().equals(DiscountType.DATE)) {
                DateDiscount dateDiscount = ((DateDiscount)discount);
                if (dateDiscount.getDate()  == showing.getShowStartTime().getDayOfMonth()
                || dateDiscount.getMonth() == showing.getShowStartTime().getMonthValue()
                || dateDiscount.getYear() == showing.getShowStartTime().getYear()) {
                    currDiscount = dollarOrPercent(movie.getTicketPrice(), discount);
                }

            } else if (discount.getType().equals(DiscountType.SEQUENCENUMBER)) {
                if (((SequenceNumberDiscount)discount).getSequenceId() == showing.getSequenceOfTheDay()) {
                    currDiscount = dollarOrPercent(movie.getTicketPrice(), discount);
                }
            } else if (discount.getType().equals(DiscountType.SHOWTIMERANGE)) {

                if (((ShowTimeRangeDiscount)discount).getStartTime().isBefore(showing.getShowStartTime())
                && ((ShowTimeRangeDiscount)discount).getEndTime().isAfter(showing.getShowStartTime())) {
                    currDiscount = dollarOrPercent(movie.getTicketPrice(), discount);
                }

            } else if (discount.getType().equals(DiscountType.SPECIALMOVIE)) {

                if (movie.getSpecialCode() == SpecialMovieDiscount.MOVIE_CODE_SPECIAL) {
                    currDiscount = dollarOrPercent(movie.getTicketPrice(), discount);
                }

            }
            maxDiscount = Math.max(maxDiscount, currDiscount);
        }
        return maxDiscount;
    }

    private double dollarOrPercent(double ticketPrice, Discount discount) {
        // here we are assuming that validation during discount creation would've made sure that only one of amount or percent were added
        if (discount.getPercent() > 0) {
            return ticketPrice * discount.getPercent() / 100;
        } else {
            return discount.getAmount();
        }
    }
}
