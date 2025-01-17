package com.concert.reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationRequest {
    private Long seatNum;
    private Long userId;
    private Long schedulesId;
    private Long concertId;
    private Long point;
}
