package com.concert.domain.reservation;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "concert_reservation")
public class ConcertReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "concert_id")
    private Long concertId;
    @Column(name = "schedules_id")
    private Long schedulesId;
    @Column(name = "seat_num")
    private Long seatNum;
    @Column(name = "payment_yn")
    private boolean paymentYn;
    @Column(name = "delete_yn")
    private boolean deleteYn;
}
