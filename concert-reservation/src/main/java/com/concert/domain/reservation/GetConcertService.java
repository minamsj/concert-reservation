package com.concert.domain.reservation;

import com.concert.reservation.component.ReservationManager;
import com.concert.interfaces.api.reservation.ConcertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConcertService {
    private final ReservationManager reservationManager;

    public List<ConcertResponse> execute() {
        return reservationManager.getConcert();
    }
}
