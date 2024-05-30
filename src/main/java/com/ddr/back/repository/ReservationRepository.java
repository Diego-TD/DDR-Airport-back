package com.ddr.back.repository;

import com.ddr.back.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r where r.user.id = :id")
    List<Reservation> findReservationsByUserId(Long id);
}
