package com.ddr.back.dto;


import jakarta.annotation.Nonnull;

public class AirportCityCountryDTO {
    @Nonnull
    private Long airportId;

    @Nonnull
    private Long cityId;

    @Nonnull
    private Long countryId;

    public AirportCityCountryDTO(@Nonnull Long airportId, @Nonnull Long cityId, @Nonnull Long countryId){
        this.airportId = airportId;
        this.cityId = cityId;
        this.countryId = countryId;
    }

    @Nonnull
    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(@Nonnull Long airportId) {
        this.airportId = airportId;
    }

    @Nonnull
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(@Nonnull Long cityId) {
        this.cityId = cityId;
    }

    @Nonnull
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(@Nonnull Long countryId) {
        this.countryId = countryId;
    }
}
