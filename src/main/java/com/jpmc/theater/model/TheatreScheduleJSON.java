package com.jpmc.theater.model;

import java.time.LocalDate;
import java.util.List;

public class TheatreScheduleJSON {
    LocalDate date;
    List<ShowingJSON> showing;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ShowingJSON> getShowing() {
        return showing;
    }

    public void setShowing(List<ShowingJSON> showing) {
        this.showing = showing;
    }
}
