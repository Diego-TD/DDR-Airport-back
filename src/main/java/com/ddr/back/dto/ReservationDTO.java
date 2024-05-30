package com.ddr.back.dto;

import jakarta.annotation.Nonnull;

public class ReservationDTO {

    @Nonnull
    private Long flightId;
    @Nonnull
    private Long userId;
    @Nonnull
    private Integer luggage;

    public ReservationDTO(@Nonnull Long flightId, @Nonnull Long userId, @Nonnull Integer luggage) {
        this.flightId = flightId;
        this.userId = userId;
        this.luggage = luggage;
    }

    @Nonnull
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(@Nonnull Long flightId) {
        this.flightId = flightId;
    }

    @Nonnull
    public Long getUserId() {
        return userId;
    }

    public void setUserId(@Nonnull Long userId) {
        this.userId = userId;
    }

    @Nonnull
    public Integer getLuggage() {
        return luggage;
    }

    public void setLuggage(@Nonnull Integer luggage) {
        this.luggage = luggage;
    }
}
