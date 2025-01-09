package com.concert.point.component;

import com.concert.point.entity.Point;
import com.concert.point.entity.PointHistory;
import com.concert.repositories.point.PointHistoryRepository;
import com.concert.repositories.point.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointManager {
    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Autowired
    public PointManager(PointRepository pointRepository, PointHistoryRepository pointHistoryRepository) {
        this.pointRepository = pointRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }

    // 잔액 조회
    public Long getPoint(Long userId) {
        Long point = pointRepository.getPoint(userId);

        // 데이터가 없으면 0으로 삽입
        if (point == null) {
            Point newPoint = new Point(userId, 0L);
            pointRepository.save(newPoint);
            return 0L;
        }

        return pointRepository.getPoint(userId);
    }

    // 잔액 충전
    public void chargePoint(Long userId, Long amount) {
        pointRepository.chargePoint(userId, amount);
    }

    public void savePointhistory(PointHistory pointHistory) {
        pointHistoryRepository.save(pointHistory);
    }
}
