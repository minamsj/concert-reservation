package com.concert.infrastructure.reservation;

import com.concert.domain.reservation.ConcertReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConcertReservationRepositoryImpl implements ConcertReservationRepository {

    private final ConcertReservationJpaRepository concertReservationJpaRepository;

    @Override
    public List<Long> findSoldOutSeats(Long schedulesId) {
        return concertReservationJpaRepository.findSoldOutSeats(schedulesId);
    }
}