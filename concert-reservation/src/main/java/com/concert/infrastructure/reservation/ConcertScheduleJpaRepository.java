package com.concert.infrastructure.reservation;

import com.concert.domain.reservation.ConcertShedules;
import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface ConcertScheduleJpaRepository extends JpaRepository<ConcertShedules, Long> {
    @Query("SELECT NEW com.concert.interfaces.api.reservation.ConcertScheduleResponse(cs.id, cs.concertId, cs.schedulesDate, cs.concertPrice) FROM ConcertShedules cs WHERE cs.concertId = :concertId")
    List<ConcertScheduleResponse> getConcertSchedule(@Param("concertId") Long concertId);
}