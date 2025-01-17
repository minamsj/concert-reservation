package com.concert.point.service;

import com.concert.point.component.PointManager;
import com.concert.point.dto.request.PointChargeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChargePointService {

    private final PointManager pointManager;

    @Transactional
    public void execute(PointChargeRequest request) {
        pointManager.chargePoint(request.getUserId(), request.getAmount());
        pointManager.savePointhistory(request);
    }
}
