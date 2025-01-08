package com.concert.point.usecase;

import com.concert.point.component.PointManager;
import com.concert.point.dto.request.PointChargeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargePointUseCase {

    private final PointManager pointManager;

    @Autowired
    public ChargePointUseCase(PointManager pointManager) {
        this.pointManager = pointManager;
    }

    public void execute(PointChargeRequest request) {
        if (request.getAmount() < 1) throw new RuntimeException();
        pointManager.chargePoint(request.getUserId(), request.getAmount());
    }
}
