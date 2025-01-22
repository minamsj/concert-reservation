package com.concert.reservation.component;

import com.concert.intrastructure.point.PointRepository;
import com.concert.intrastructure.reservation.ConcertRepository;
import com.concert.intrastructure.reservation.ConcertReservationRepository;
import com.concert.intrastructure.reservation.ConcertSheduleRepository;
import com.concert.interfaces.api.reservation.ReservationRequest;
import com.concert.interfaces.api.reservation.ConcertResponse;
import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import com.concert.interfaces.api.reservation.ConcertSeatResponse;
import com.concert.domain.reservation.ConcertReservation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ReservationManager {
    private final ConcertRepository concertRepository;
    private final ConcertSheduleRepository concertSheduleRepository;
    private final ConcertReservationRepository concertReservationRepository;
    private final PointRepository pointRepository;

    // 콘서트 조회
    public List<ConcertResponse> getConcert() {
        return concertRepository.getConcert();
    }

    // 콘서트 회차 조회
    public List<ConcertScheduleResponse> getConcertShedules(Long concertId) {
        return concertSheduleRepository.getConcertShedule(concertId);
    }

    public List<ConcertSeatResponse> getAvailableSeats(Long schedulesId) {
        List<Long> soldOutSeats = concertReservationRepository.findSoldOutSeats(schedulesId);
        return generateSeatList(soldOutSeats);
    }

    private List<ConcertSeatResponse> generateSeatList(List<Long> soldOutSeats) {
        return IntStream.rangeClosed(1, 50)
                .mapToObj(seatNum -> ConcertSeatResponse.builder()
                        .seatNum((long) seatNum)
                        .isSoldOut(soldOutSeats.contains((long) seatNum))
                        .build())
                .collect(Collectors.toList());
    }

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
}
