package com.ddr.back.repository;

import com.ddr.back.entity.ReservationRoundTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRoundTripRepository extends JpaRepository<ReservationRoundTrip, Long> {

}
