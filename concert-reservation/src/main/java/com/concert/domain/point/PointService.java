package com.concert.domain.point;

import com.concert.intrastructure.point.PointHistoryRepository;
import com.concert.intrastructure.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;

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

    public void chargePoint(Long userId, Long amount) {
        if (amount < 10000) throw new RuntimeException();
        Long point = pointRepository.getPoint(userId);
        if (point == null) {
            pointRepository.save(new Point(userId, amount));
        } else {
            pointRepository.chargePoint(userId, amount);
        }
    }

    public void savePointHistory(Long userId, Long amount) {
        PointHistory pointHistory = PointHistory.builder()
                .userId(userId)
                .point(amount)
                .type("charge")
                .build();

        pointHistoryRepository.save(pointHistory);
    }
}
