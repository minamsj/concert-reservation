package com.concert.reservation.component;

import com.concert.repositories.reservation.ConcertRepository;
import com.concert.repositories.reservation.ConcertReservationRepository;
import com.concert.repositories.reservation.ConcertSheduleRepository;
import com.concert.reservation.dto.response.ConcertResponse;
import com.concert.reservation.dto.response.ConcertScheduleResponse;
import com.concert.reservation.dto.response.ConcertSeatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ReservationManager {
    private final ConcertRepository concertRepository;
    private final ConcertSheduleRepository concertSheduleRepository;
    private final ConcertReservationRepository concertReservationRepository;

    @Autowired
    public ReservationManager(ConcertRepository concertRepository, ConcertSheduleRepository concertSheduleRepository, ConcertReservationRepository concertReservationRepository) {
        this.concertRepository = concertRepository;
        this.concertSheduleRepository = concertSheduleRepository;
        this.concertReservationRepository = concertReservationRepository;
    }

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

}
