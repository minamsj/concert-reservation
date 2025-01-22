package com.concert.interfaces.api.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertSeatResponse {
    private Long seatNum;
    private boolean isSoldOut;
}
