package com.concert.repositories.point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.concert.point.entity.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("SELECT p.point FROM Point p WHERE p.userId = :userId")
    Long getPoint(Long userId);
    @Modifying
    @Query("UPDATE Point p SET p.point = p.point + :amount WHERE p.userId = :userId")
    void chargePoint(@Param("userId") Long userId, @Param("amount") Long amount);

}
