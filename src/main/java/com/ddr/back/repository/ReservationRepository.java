package com.ddr.back.repository;

import com.ddr.back.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // You can define custom query methods here
}
