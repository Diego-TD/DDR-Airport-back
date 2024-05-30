package com.ddr.back.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Integer luggage;

    public Reservation(){

    }

    public Reservation(Long id, Flight flight, User user, LocalDateTime createdAt, Integer luggage) {
        this.id = id;
        this.flight = flight;
        this.user = user;
        this.createdAt = createdAt;
        this.luggage = luggage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getLuggage() {
        return luggage;
    }

    public void setLuggage(Integer luggage) {
        this.luggage = luggage;
    }
}
