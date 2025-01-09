package com.concert.repositories.reservation;

import com.concert.reservation.dto.request.ReservationRequest;
import com.concert.reservation.entity.ConcertReservation;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertReservationRepository extends JpaRepository<ConcertReservation, Long> {
    @Query("SELECT cr.seatNum FROM ConcertReservation cr WHERE cr.paymentYn = true AND cr.schedulesId = :schedulesId AND cr.deleteYn = false")
    List<Long> findSoldOutSeats(@Param("schedulesId") Long schedulesId);
}
