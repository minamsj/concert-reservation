package com.concert.point.usecase;

import com.concert.point.component.PointManager;
import com.concert.point.dto.request.PointChargeRequest;
import com.concert.point.entity.PointHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChargePointUseCase {

    private final PointManager pointManager;

    @Autowired
    public ChargePointUseCase(PointManager pointManager) {
        this.pointManager = pointManager;
    }

    @Transactional
    public void execute(PointChargeRequest request) {
        if (request.getAmount() < 1) throw new RuntimeException();
        pointManager.chargePoint(request.getUserId(), request.getAmount());

        PointHistory pointHistory = PointHistory.builder()
                .userId(request.getUserId())
                .point(request.getAmount())
                .type("charge")
                .build();

        pointManager.savePointhistory(pointHistory);
    }
}
