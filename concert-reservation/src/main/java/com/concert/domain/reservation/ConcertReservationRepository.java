package com.concert.domain.reservation;

import java.util.List;

public interface ConcertReservationRepository {
    List<Long> findSoldOutSeats(Long schedulesId);
}
