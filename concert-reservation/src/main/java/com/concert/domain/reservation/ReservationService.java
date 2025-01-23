package com.concert.domain.reservation;

import com.concert.interfaces.api.reservation.ConcertResponse;
import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import com.concert.interfaces.api.reservation.ConcertSeatResponse;
import com.concert.intrastructure.point.PointRepository;
import com.concert.intrastructure.reservation.ConcertRepository;
import com.concert.intrastructure.reservation.ConcertReservationRepository;
import com.concert.intrastructure.reservation.ConcertSheduleRepository;
import com.concert.interfaces.api.reservation.ReservationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ConcertRepository concertRepository;
    private final ConcertSheduleRepository concertSheduleRepository;
    private final ConcertReservationRepository concertReservationRepository;
    private final PointRepository pointRepository;

    public void createReservation(@Valid ReservationRequest request) {

        ConcertReservation reservation = ConcertReservation.builder()
                .schedulesId(request.getSchedulesId())
                .seatNum(request.getSeatNum())
                .userId(request.getUserId())
                .concertId(request.getConcertId())
                .build();

        concertReservationRepository.save(reservation);

        pointRepository.chargePoint(request.getUserId(), -request.getPoint());
    }

    public List<ConcertScheduleResponse> getConcertShedules(Long concertId) {
        return concertSheduleRepository.getConcertShedule(concertId);
    }

    public List<ConcertResponse> getConcert() {
        return concertRepository.getConcert();
    }

}
