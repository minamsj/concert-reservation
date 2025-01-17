package com.concert.reservation.service;

import com.concert.reservation.component.ReservationManager;
import com.concert.reservation.dto.response.ConcertSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSeatService {

    private final ReservationManager reservationManager;

    public List<ConcertSeatResponse> execute(Long schedulesId) {
        return reservationManager.getAvailableSeats(schedulesId);
    }
}
