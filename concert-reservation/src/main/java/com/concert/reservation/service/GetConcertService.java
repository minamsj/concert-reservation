package com.concert.reservation.service;

import com.concert.reservation.component.ReservationManager;
import com.concert.reservation.dto.response.ConcertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
