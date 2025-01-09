package com.concert.reservation.usecase;

import com.concert.reservation.component.ReservationManager;
import com.concert.reservation.dto.response.ConcertResponse;
import com.concert.reservation.dto.response.ConcertScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetConcertUseCase {
    private final ReservationManager reservationManager;

    @Autowired
    public GetConcertUseCase(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    public List<ConcertResponse> execute() {
        return reservationManager.getConcert();
    }
}
