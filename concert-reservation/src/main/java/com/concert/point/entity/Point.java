package com.concert.point.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "point")
    private Long point;

    public Point() {}

    public Point(Long userId, Long point) {
        this.userId = userId;
        this.point = point;
    }
}
