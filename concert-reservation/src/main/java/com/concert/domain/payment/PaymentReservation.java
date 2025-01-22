package com.concert.domain.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "concert_reservation")
public class PaymentReservation {
    @Id
    @Column(name = "reservation_id")
    private Long reservationId;
    @Column(name = "payment_yn")
    private boolean paymentYn;
}
