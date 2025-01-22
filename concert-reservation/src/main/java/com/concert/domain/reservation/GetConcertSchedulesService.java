package com.concert.domain.reservation;

import com.concert.reservation.component.ReservationManager;
import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConcertSchedulesService {

    private final ReservationManager reservationManager;

    public List<ConcertScheduleResponse> getConcertShedules(Long concertId) {
        return reservationManager.getConcertShedules(concertId);
    }
}
