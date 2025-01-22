package com.concert.application.reservation;

import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import com.concert.domain.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReqservationFacade {

    private final ReservationService reservationService;

    public List<ConcertScheduleResponse> getConcertShedules(Long concertId) {
        return reservationService.getConcertShedules(concertId);
    }
}
