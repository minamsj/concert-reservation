package com.concert.domain.point;

import com.concert.infrastructure.point.PointHistoryRepository;
import com.concert.infrastructure.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private static final int MAX_RETRIES = 3;

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

        Point point = pointRepository.findByUserId(userId)
                .orElse(new Point(userId, 0L));

        for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
            try {
                if (point.getId() == null) {
                    point.setPoint(amount);
                    pointRepository.save(point);
                    return;
                }

                int updated = pointRepository.chargePoint(userId, amount, point.getVersion());
                if (updated > 0) return;

                point = pointRepository.findByUserId(userId).orElseThrow();

            } catch (OptimisticLockingFailureException e) {
                if (attempt == MAX_RETRIES - 1) throw new RuntimeException("포인트 충전 실패", e);
            }
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
