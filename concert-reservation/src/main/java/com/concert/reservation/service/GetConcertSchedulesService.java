package com.concert.reservation.service;

import com.concert.reservation.component.ReservationManager;
import com.concert.reservation.dto.response.ConcertScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConcertSchedulesService {

    private final ReservationManager reservationManager;

    public List<ConcertScheduleResponse> execute(Long concertId) {
        return reservationManager.getConcertShedules(concertId);
    }
}
