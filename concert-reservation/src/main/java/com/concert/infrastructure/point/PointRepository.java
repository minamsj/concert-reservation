package com.concert.infrastructure.point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.concert.domain.point.Point;

import java.util.Optional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("SELECT p.point FROM Point p WHERE p.userId = :userId")
    Long getPoint(Long userId);
    @Modifying
    @Query("UPDATE Point p SET p.point = p.point + :amount WHERE p.userId = :userId AND p.version = :version")
    int chargePoint(@Param("userId") Long userId, @Param("amount") Long amount, @Param("version") Integer version);
    Optional<Point> findByUserId(Long userId);
}
