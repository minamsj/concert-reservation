package com.concert.domain.reservation;

import com.concert.infrastructure.reservation.ConcertReservationJpaRepository;
import com.concert.interfaces.api.reservation.ConcertSeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class GetSeatService {
    private final ConcertReservationRepository concertReservationRepository;  // 인터페이스 의존

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
