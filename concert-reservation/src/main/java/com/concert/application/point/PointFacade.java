package com.concert.application.point;

import com.concert.domain.point.PointService;
import com.concert.interfaces.api.point.PointChargeRequest;
import com.concert.interfaces.api.point.PointGetResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;

    @Transactional
    public PointGetResponse getPoint(Long userId) {
        Long point = pointService.getPoint(userId);
        return new PointGetResponse(point);
    }

    @Transactional
    public void chargePoint(Long userId, Long amount) {
        pointService.chargePoint(userId, amount);
        pointService.savePointHistory(userId, amount);
    }
}
