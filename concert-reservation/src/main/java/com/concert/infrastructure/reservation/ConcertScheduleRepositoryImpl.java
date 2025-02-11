package com.concert.infrastructure.reservation;

import com.concert.domain.reservation.ConcertScheduleRepository;
import com.concert.interfaces.api.reservation.ConcertScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertScheduleRepositoryImpl implements ConcertScheduleRepository {

    private final ConcertScheduleJpaRepository concertScheduleJpaRepository;

    @Override
    public List<ConcertScheduleResponse> getConcertSchedule(Long concertId) {
        return concertScheduleJpaRepository.getConcertSchedule(concertId);
    }
}
