package com.ddr.back.dto;

import jakarta.annotation.Nonnull;

public class ReservationRoundTripDTO extends ReservationDTO{
    @Nonnull
    private Long returnFlightId;

    public ReservationRoundTripDTO(@Nonnull Long flightId, @Nonnull Long returnFlightId, @Nonnull Long userId, @Nonnull Integer luggage) {
        super(flightId, userId, luggage);
        this.returnFlightId = returnFlightId;
    }

    @Nonnull
    public Long getReturnFlightId() {
        return returnFlightId;
    }

    public void setReturnFlightId(@Nonnull Long returnFlightId) {
        this.returnFlightId = returnFlightId;
    }
}
