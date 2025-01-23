package com.concert.application.reservation;

import com.concert.domain.reservation.GetSeatService;
import com.concert.interfaces.api.reservation.ConcertResponse;
import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import com.concert.domain.reservation.ReservationService;
import com.concert.interfaces.api.reservation.ConcertSeatResponse;
import com.concert.interfaces.api.reservation.ReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReqservationFacade {

    private final ReservationService reservationService;
    private final GetSeatService getSeatService;

    public List<ConcertScheduleResponse> getConcertShedules(Long concertId) {
        return reservationService.getConcertShedules(concertId);
    }

    public List<ConcertResponse> getConcert() {
        return reservationService.getConcert();
    }

    public List<ConcertSeatResponse> getAvailableSeats(Long schedulesId) {
        return getSeatService.getAvailableSeats(schedulesId);
    }

    public void createReservation(ReservationRequest request) {
        reservationService.createReservation(request);
    }
}
