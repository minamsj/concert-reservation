package com.concert.point.component;

import com.concert.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointManager {
    private final PointRepository pointRepository;

    @Autowired
    public PointManager(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    // 잔액 충전
    public void chargePoint(Long userId, Long amount) {
        pointRepository.chargePoint(userId, amount);
    }

    // 잔액 조회
    public Long getPoint(Long userId) {
        return pointRepository.getPoint(userId);
    }
}
