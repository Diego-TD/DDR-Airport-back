package com.ddr.back.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class ReservationRoundTrip extends Reservation{
    @ManyToOne
    @JoinColumn(nullable = false)
    private Flight returnFlight;

    public ReservationRoundTrip(Long id, Flight flight, Flight returnFlight, User user, LocalDateTime createdAt, String luggage, Double total) {
        super(id, flight, user, createdAt, luggage, total);
        this.returnFlight = returnFlight;
    }

    public ReservationRoundTrip() {

    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }
}
