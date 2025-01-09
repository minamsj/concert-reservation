package com.concert.payment.dto.request;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PaymentRequest {
    private final Long concertId;
    private final Long userId;
    private final Long amount;
    private final Long reservationId;
    private final Long tokenId;
}
