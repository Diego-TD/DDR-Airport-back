package com.ddr.back.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Airplane airplane;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Airport arrivalAirport;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Time time;

    public Flight() {

    }

    public Flight(Long id, Airplane airplane, Airport departureAirport, Airport arrivalAirport, Date date, Time time) {
        this.id = id;
        this.airplane = airplane;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
