package com.concert.payment.entity;

import com.concert.reservation.entity.ConcertReservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

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
