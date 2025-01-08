package com.concert.point.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Point")
public class Point {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "point")
    private Long point;
}
