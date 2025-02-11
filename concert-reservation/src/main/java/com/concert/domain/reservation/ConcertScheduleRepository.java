package com.concert.domain.reservation;

import com.concert.interfaces.api.reservation.ConcertScheduleResponse;

import java.util.List;

public interface ConcertScheduleRepository {
    List<ConcertScheduleResponse> getConcertSchedule(Long concertId);

}
