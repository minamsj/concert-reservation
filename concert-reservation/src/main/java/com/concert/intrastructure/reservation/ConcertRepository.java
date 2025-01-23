package com.concert.intrastructure.reservation;

import com.concert.interfaces.api.reservation.ConcertResponse;
import com.concert.domain.reservation.Concert;  // Concert 엔티티 import
import org.springframework.data.jpa.repository.JpaRepository;  // JpaRepository import
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    @Query("SELECT NEW com.concert.interfaces.api.reservation.ConcertResponse(c.id, c.concertTitle) FROM Concert c")
    List<ConcertResponse> getConcert();
}
