package com.concert.intrastructure.reservation;

import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import com.concert.domain.reservation.ConcertShedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertSheduleRepository extends JpaRepository<ConcertShedules, Long> {
    @Query("SELECT NEW com.concert.interfaces.api.reservation.ConcertScheduleResponse(cs.id, cs.concertId, cs.schedulesDate, cs.concertPrice) FROM ConcertShedules cs WHERE cs.concertId = :concertId")
    List<ConcertScheduleResponse> getConcertShedule(@Param("concertId") Long concertId);
}
