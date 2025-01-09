package com.concert.reservation.usecase;

import com.concert.reservation.component.ReservationManager;
import com.concert.reservation.dto.response.ConcertSeatResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSeatUseCase {

    private final ReservationManager reservationManager;

    public GetSeatUseCase(ReservationManager reservationManager) {
        this.reservationManager = reservationManager;
    }

    public List<ConcertSeatResponse> execute(Long schedulesId) {
        return reservationManager.getAvailableSeats(schedulesId);
    }
}
