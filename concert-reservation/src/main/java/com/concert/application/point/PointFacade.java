package com.concert.application.point;

import com.concert.domain.point.PointService;
import com.concert.interfaces.api.point.PointChargeRequest;
import com.concert.interfaces.api.point.PointGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;

    public PointGetResponse getPoint(Long userId) {
        Long point = pointService.getPoint(userId);
        return new PointGetResponse(point);
    }

    public void chargePoint(PointChargeRequest request) {
        pointService.chargePoint(request.getUserId(), request.getAmount());
        pointService.savePointHistory(request.getUserId(), request.getAmount());
    }
}
