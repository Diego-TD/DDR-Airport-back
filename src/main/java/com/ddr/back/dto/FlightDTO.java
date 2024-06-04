package com.ddr.back.dto;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.sql.Date;
import java.sql.Time;

public class FlightDTO {
    @Nullable
    private Long airplaneId;
    @Nonnull
    private Long departureAirportId;
    @Nonnull
    private Long arrivalAirportId;
    @Nonnull
    private Date date;
    @Nullable
    private Time time;

    public FlightDTO() {
    }

    public FlightDTO(@Nullable Long airplaneId, @Nonnull Long departureAirportId, @Nonnull Long arrivalAirportId, @Nonnull Date date, @Nullable Time time) {
        this.airplaneId = airplaneId;
        this.departureAirportId = departureAirportId;
        this.arrivalAirportId = arrivalAirportId;
        this.date = date;
        this.time = time;
    }

    @Nullable
    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(@Nullable Long airplaneId) {
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

    @Nullable
    public Time getTime() {
        return time;
    }

    public void setTime(@Nullable Time time) {
        this.time = time;
    }
}
