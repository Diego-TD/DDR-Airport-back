package com.ddr.back.dto;

import jakarta.annotation.Nonnull;

import java.sql.Date;
import java.sql.Time;

public class FlightDTO {
    @Nonnull
    private Long airplaneId;
    @Nonnull
    private Long departureAirportId;
    @Nonnull
    private Long arrivalAirportId;
    @Nonnull
    private Date date;
    @Nonnull
    private Time time;

    public FlightDTO(@Nonnull Date date, @Nonnull Time time, @Nonnull Long airplaneId, @Nonnull Long departureAirportId, @Nonnull Long arrivalAirportId) {
        this.airplaneId = airplaneId;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.date = date;
        this.time = time;
    }

    @Nonnull
    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(@Nonnull Long airplaneId) {
        this.airplaneId = airplaneId;
    }

    @Nonnull
    public Long getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(@Nonnull Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    @Nonnull
    public Long getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(@Nonnull Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    @Nonnull
    public Date getDate() {
        return date;
    }

    public void setDate(@Nonnull Date date) {
        this.date = date;
    }

    @Nonnull
    public Time getTime() {
        return time;
    }

    public void setTime(@Nonnull Time time) {
        this.time = time;
    }
}
