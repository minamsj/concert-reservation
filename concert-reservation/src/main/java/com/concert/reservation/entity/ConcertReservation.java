package com.concert.reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "concert_reservation")
public class ConcertReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "schedules_id")
    private Long schedulesId;

    @Column(name = "seat_num")
    private Long seatNum;

    @Column(name = "payment_yn")
    private boolean paymentYn;

    @Column(name = "delete_yn")
    private boolean deleteYn;
}
