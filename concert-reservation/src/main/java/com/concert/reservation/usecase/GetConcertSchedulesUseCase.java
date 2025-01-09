package com.concert.reservation.usecase;

import com.concert.reservation.component.ReservationManager;
import com.concert.reservation.dto.response.ConcertScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetConcertSchedulesUseCase {

    private final ReservationManager reservationManager;

    @Autowired
    public GetConcertSchedulesUseCase(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    public List<ConcertScheduleResponse> execute(Long concertId) {
        return reservationManager.getConcertShedules(concertId);
    }
}
