package com.concert.interfaces.api.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationRequest {
    private Long seatNum;
    private Long userId;
    private Long schedulesId;
    private Long concertId;
    private Long point;
}
