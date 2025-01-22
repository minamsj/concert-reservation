package com.concert.domain.reservation;

import com.concert.reservation.component.ReservationManager;
import com.concert.interfaces.api.reservation.ConcertSeatResponse;
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
