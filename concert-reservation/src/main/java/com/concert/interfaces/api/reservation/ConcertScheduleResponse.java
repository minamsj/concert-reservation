package com.concert.interfaces.api.reservation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ConcertScheduleResponse {
    private final long concert_id;
    private final long schedules_id;
    private final LocalDateTime schedules_date;
    private final long concert_price;
}
