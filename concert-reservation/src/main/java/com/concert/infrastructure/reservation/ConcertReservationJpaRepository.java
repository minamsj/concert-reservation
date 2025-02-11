package com.concert.infrastructure.reservation;

import com.concert.domain.reservation.ConcertReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertReservationJpaRepository extends JpaRepository<ConcertReservation, Long> {
    @Query("SELECT cr.seatNum FROM ConcertReservation cr WHERE cr.paymentYn = true AND cr.schedulesId = :schedulesId AND cr.deleteYn = false")
    List<Long> findSoldOutSeats(@Param("schedulesId") Long schedulesId);
}
