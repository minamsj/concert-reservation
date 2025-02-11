package com.concert.domain.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "concert_schedules")
public class ConcertShedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedules_id")
    private Long id;

    @Column(name = "concert_id")
    private Long concertId;
    @Column(name = "schedules_date")
    private LocalDateTime schedulesDate;
    @Column(name = "concertPrice")
    private Long concertPrice;
}
